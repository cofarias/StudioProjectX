package com.studioprojectx

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.AllOut
import androidx.compose.material.icons.filled.BubbleChart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.studioprojectx.navigation.authGraph
import com.studioprojectx.navigation.homeGraph
import com.studioprojectx.navigation.navigateToAuthGraph
import com.studioprojectx.navigation.navigateToEditTaskForm
import com.studioprojectx.navigation.navigateToHomeGraph
import com.studioprojectx.navigation.navigateToNewTaskForm
import com.studioprojectx.navigation.navigateToSignIn
import com.studioprojectx.navigation.navigateToSignUp
import com.studioprojectx.navigation.navigateToTasksList
import com.studioprojectx.navigation.splashScreen
import com.studioprojectx.navigation.splashScreenRoute
import com.studioprojectx.ui.theme.StudioProjectXTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

//    private lateinit var binding: ActivityMainBinding
    private lateinit var remoteConfig: FirebaseRemoteConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        remoteConfig = Firebase.remoteConfig

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }

        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val showButton = remoteConfig.getBoolean("show_button_facebook")


                } else {
                    Log.i("Info teste", "Error: ${task.exception?.printStackTrace()}")
                }
            }

        setContent {
            StudioProjectXTheme {
                val navController = rememberNavController()
                val appViewModel = koinViewModel<AppViewModel>()
                val appState by appViewModel.state.collectAsState(initial = AppState())
                val itemsBottomMenu = remember {
                    listOf(
                        Pair(
                            "Tela A",
                            Icons.Filled.AdsClick
                        ),
                        Pair(
                            "Tela B",
                            Icons.Filled.AllOut
                        ),
                        Pair(
                            "Tela C",
                            Icons.Filled.BubbleChart
                        ),
                    )
                }
                var selectedItem by remember {
                    mutableStateOf(itemsBottomMenu.first())
                }
                LaunchedEffect(appState) {
                    if (appState.isInitLoading) {
                        return@LaunchedEffect
                    }

                    appState.user?.let {
                        navController.navigateToHomeGraph()
                    } ?: navController.navigateToAuthGraph()
                }

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(
                        modifier = Modifier.weight(1f),
                        navController = navController,
                        startDestination = splashScreenRoute
                    ) {
                        splashScreen()

                        authGraph(
                            onNavigateToSignIn = {
                                navController.navigateToSignIn(it)
                            },
                            onNavigateToSignUp = {
                                navController.navigateToSignUp()
                            }
                        )

                        homeGraph(
                            onNavigateToTaskList = {
                                navController.navigateToTasksList()
                            },
                            onNavigateToNewTaskForm = {
                                navController.navigateToNewTaskForm()
                            },
                            onNavigateToEditTaskForm = { task ->
                                navController.navigateToEditTaskForm(task)
                            },
                            onPopBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    BottomAppBar(
                        actions = {
                            itemsBottomMenu.forEach { item ->
                                NavigationBarItem(
                                    label = { item.first },
                                    selected = selectedItem == item,
                                    onClick = {
                                        selectedItem = item
//                                        val route = when (item.first) {
//                                            "Tela A" -> TasksListScreen(uiState = TasksListUIState())
//                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = item.second,
                                            contentDescription = "Ícone ",
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .size(40.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.inverseOnSurface,
                                                    CircleShape
                                                )
                                                .padding(1.dp),
                                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                                        )
                                    }
                                )
                            }

                        }
                    )
                }
            }
        }
    }
}