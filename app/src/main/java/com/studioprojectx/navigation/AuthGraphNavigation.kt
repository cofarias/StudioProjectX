package com.studioprojectx.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navigation

const val authGraphRoute = "authGraph"


fun NavGraphBuilder.authGraph(
    onNavigateToSignUp: () -> Unit,
    onNavigateToHomeGraph: (NavOptions) -> Unit,
    onNavigateToSignIn: (NavOptions) -> Unit
) {
    navigation(
        route = authGraphRoute,
        startDestination = signUpRoute
    ) {
        signUpScreen(
            onNavigationToSignIn = onNavigateToSignUp
        )
    }
}

fun NavHostController.navigateToAuthGraph() {
    navigate(authGraphRoute)
}