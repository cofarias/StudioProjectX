package com.studioprojectx.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.studioprojectx.authentication.FirebaseAuthRepository
import com.studioprojectx.features.auth.signup.SignUpViewModel
import com.studioprojectx.models.Task

const val tasksListRoute = "tasksList"

fun NavGraphBuilder.tasksListScreen(
    onNavigateToNewTaskForm: () -> Unit,
    onNavigateToEditTaskForm: (Task) -> Unit
) {
    composable(tasksListRoute) {
        val viewModel = SignUpViewModel(
            firebaseAuthRepository = FirebaseAuthRepository(
                Firebase.auth
            )
        )

        val uiState by viewModel.uiState.collectAsState()
    }

//    TasksListScreen(
//        uiState = uiS
//    )
}

fun NavHostController.navigateToTasksList() {
    navigate(tasksListRoute)
}