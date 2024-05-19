package com.studioprojectx

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.studioprojectx.navigation.authGraph
import com.studioprojectx.navigation.authGraphRoute
import com.studioprojectx.navigation.homeGraph
import com.studioprojectx.navigation.navigateToEditTaskForm
import com.studioprojectx.navigation.navigateToHomeGraph
import com.studioprojectx.navigation.navigateToNewTaskForm
import com.studioprojectx.navigation.navigateToSignIn
import com.studioprojectx.navigation.navigateToSignUp
import com.studioprojectx.ui.theme.StudioprojectxTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = Firebase.auth
        Log.i(TAG, "onCreate do usuário atual: ${auth.currentUser}")

        auth.createUserWithEmailAndPassword(
            "fariasoc@outlook.com",
            "123456"
        ).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Log.i(TAG, "Usuário criado com sucesso")
            } else {
                Log.i(TAG, "Falha ao criar o usuário-> ${task.exception}")
            }
        }

        auth.createUserWithEmailAndPassword(
            "fariasoc@outlook.com",
            "123456"
        )

        setContent {
            StudioprojectxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = authGraphRoute
                    ) {
                        authGraph(
                            onNavigateToHomeGraph = {
                                navController.navigateToHomeGraph(it)
                            },
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

                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StudioprojectxTheme {
        Greeting("Android")
    }
}