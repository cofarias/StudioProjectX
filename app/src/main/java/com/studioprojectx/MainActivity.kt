package com.studioprojectx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.studioprojectx.navigation.authGraph
import com.studioprojectx.navigation.homeGraph
import com.studioprojectx.navigation.navigateToAuthGraph
import com.studioprojectx.navigation.navigateToEditTaskForm
import com.studioprojectx.navigation.navigateToHomeGraph
import com.studioprojectx.navigation.navigateToNewTaskForm
import com.studioprojectx.navigation.navigateToSignIn
import com.studioprojectx.navigation.navigateToSignUp
import com.studioprojectx.navigation.splashScreen
import com.studioprojectx.navigation.splashScreenRoute
import com.studioprojectx.ui.theme.StudioProjectXTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StudioProjectXTheme {
                val navController = rememberNavController()
                val appViewModel = koinViewModel<AppViewModel>()
                val appState by appViewModel.state.collectAsState(initial = AppState())

                LaunchedEffect(appState) {
                    if (appState.isInitLoading) {
                        return@LaunchedEffect
                    }

                    appState.user?.let {
                        navController.navigateToHomeGraph()
                    } ?: navController.navigateToAuthGraph()
                }

                NavHost(
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
            }
        }
    }
}