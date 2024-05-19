package com.studioprojectx.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.studioprojectx.authentication.FirebaseAuthRepository
import com.studioprojectx.features.auth.signup.SignUpScreen
import com.studioprojectx.features.auth.signup.SignUpViewModel
import kotlinx.coroutines.launch

const val signInRoute: String = "signIn"

fun NavGraphBuilder.signInScreen(
    onNavigateToTasksList: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    composable(signInRoute) {
        val viewModel = SignUpViewModel(firebaseAuthRepository = FirebaseAuthRepository(firebaseAuth = Firebase.auth))
        val uiState by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        val signUpIsSuccessful by viewModel.signUpIsSuccessful.collectAsState(initial = false)

        LaunchedEffect(signUpIsSuccessful) {
            if(signUpIsSuccessful)
                onNavigateToTasksList()
        }

        SignUpScreen(
            uiState = uiState,
            onSignUpClick = {
                scope.launch {
                    viewModel.signUp()
                }
            }
        )
    }
}

fun NavHostController.navigateToSignIn(
    navOptions: NavOptions? = null
) {
    navigate(signInRoute, navOptions)
}