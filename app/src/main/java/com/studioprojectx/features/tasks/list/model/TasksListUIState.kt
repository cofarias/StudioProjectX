package com.studioprojectx.features.tasks.list.model

import com.studioprojectx.models.Task

data class TasksListUIState(
    val tasks: List<Task> = emptyList(),
    val onTaskDoneChange: (Task) -> Unit = {},
    val user: String? = null
)
