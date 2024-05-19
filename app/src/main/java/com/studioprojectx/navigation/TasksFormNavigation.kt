package com.studioprojectx.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.studioprojectx.authentication.FirebaseAuthRepository
import com.studioprojectx.features.auth.signup.SignUpViewModel
import com.studioprojectx.models.Task
import androidx.navigation.navArgument

const val taskFormRoute = "taskForm"
const val taskIdArgument = "taskId"

fun NavGraphBuilder.taskFormScreen(
    onPopBackStack: () -> Unit,
) {
    composable("$taskFormRoute?$taskIdArgument={$taskIdArgument}") {
        val taskId = navArgument(taskIdArgument) {
            nullable = true
        }

        val scope = rememberCoroutineScope()
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

fun NavHostController.navigateToNewTaskForm() {
    navigate(taskFormRoute)
}

fun NavHostController.navigateToEditTaskForm(task: Task) {
    navigate("$taskFormRoute?$taskIdArgument=${task.id}")
}