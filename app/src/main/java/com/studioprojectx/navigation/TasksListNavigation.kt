package com.studioprojectx.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.studioprojectx.features.tasks.list.TaskListViewModel
import com.studioprojectx.features.tasks.list.TasksListScreen
import com.studioprojectx.features.tasks.list.model.TasksListUIState
import com.studioprojectx.features.tasks.model.Task
import org.koin.androidx.compose.koinViewModel

const val tasksListRoute = "tasksList"

fun NavGraphBuilder.tasksListScreen(
    onNavigateToNewTaskForm: () -> Unit,
    onNavigateToEditTaskForm: (Task) -> Unit
) {
    composable(tasksListRoute) {
        val viewModel = koinViewModel<TaskListViewModel>()
        val uiState by viewModel.uiState.collectAsState(TasksListUIState())

        TasksListScreen(
            uiState = uiState,
            onNewTaskClick = onNavigateToNewTaskForm,
            onTaskClick = onNavigateToEditTaskForm,
            onExitToAppClick = {
                viewModel.signOut()
            }
        )
    }
}

fun NavHostController.navigateToTasksList() {
    navigate(tasksListRoute)
}