package com.studioprojectx.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.studioprojectx.features.home.HomeScreen
import com.studioprojectx.features.home.HomeViewModel
import com.studioprojectx.features.home.model.HomeUIState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

const val homeRoute = "homeRoute"

fun NavGraphBuilder.homeScreen(
    onNavigateToNewTaskForm: () -> Unit,
    onNavigateToTaskList: () -> Unit,
) {
    composable(homeRoute) {
        val viewModel = koinViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsState(initial = HomeUIState())
        val scope = rememberCoroutineScope()

        HomeScreen(
            onNewTaskClick = onNavigateToNewTaskForm,
            onTaskListClick = onNavigateToTaskList,
            uiState = uiState,
            onEvent = {
                scope.launch {
                    viewModel.addProductToFirestore()
                }
            }
        )
    }
}

//fun NavHostController.navigateToTasksList() {
//    navigate(tasksListRoute)
//}