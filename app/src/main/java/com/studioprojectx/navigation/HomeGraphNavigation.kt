package com.studioprojectx.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.studioprojectx.models.Task
import androidx.navigation.NavOptions
import androidx.navigation.navigation

const val homeGraphRoute = "homeGraph"

fun NavGraphBuilder.homeGraph(
    onNavigateToNewTaskForm: () -> Unit,
    onNavigateToEditTaskForm: (Task) -> Unit,
    onPopBackStack: () -> Unit
) {
    navigation(
        startDestination = tasksListRoute,
        route = homeGraphRoute
    ) {
        tasksListScreen(
            onNavigateToNewTaskForm = onNavigateToNewTaskForm,
            onNavigateToEditTaskForm = onNavigateToEditTaskForm
        )
        taskFormScreen(onPopBackStack = onPopBackStack)
    }
}

fun NavHostController.navigateToHomeGraph(
    navOptions: NavOptions? = null
) {
    navigate(homeGraphRoute, navOptions)
}