package com.studioprojectx.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.studioprojectx.features.home.HomeScreen
import com.studioprojectx.features.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

const val homeRoute = "homeRoute"

fun NavGraphBuilder.homeScreen(
    onNavigateToNewTaskForm: () -> Unit,
    onNavigateToTaskList: () -> Unit,
) {
    composable(homeRoute) {
        val viewModel = koinViewModel<HomeViewModel>()
        //val uiState by viewModel.uiState.collectAsState(initial = HomeUIState())

        HomeScreen(
            onNewTaskClick = onNavigateToNewTaskForm,
            onTaskListClick = onNavigateToTaskList
            //uiState = uiState,
        )
    }
}

//fun NavHostController.navigateToTasksList() {
//    navigate(tasksListRoute)
//}