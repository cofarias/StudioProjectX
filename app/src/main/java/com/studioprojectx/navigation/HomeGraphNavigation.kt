package com.studioprojectx.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.studioprojectx.features.tasks.model.Task

const val homeGraphRoute = "homeGraph"

fun NavGraphBuilder.homeGraph(
    onNavigateToTaskList: () -> Unit,
    onNavigateToNewTaskForm: () -> Unit,
    onNavigateToEditTaskForm: (Task) -> Unit,
    onPopBackStack: () -> Unit
) {
    navigation(
        //startDestination = tasksListRoute,
        startDestination = homeRoute,
        route = homeGraphRoute
    ) {
        homeScreen(
            onNavigateToNewTaskForm = onNavigateToNewTaskForm,
            onNavigateToTaskList = onNavigateToTaskList
        )

        tasksListScreen(
            onNavigateToNewTaskForm = onNavigateToNewTaskForm,
            onNavigateToEditTaskForm = onNavigateToEditTaskForm
        )
        taskFormScreen(onPopBackStack = onPopBackStack)
    }
}

fun NavHostController.navigateToHomeGraph(
    navOptions: NavOptions? = navOptions {
        popUpTo(graph.id) {
            inclusive = true
        }
    }
) {
    navigate(homeGraphRoute, navOptions)
}