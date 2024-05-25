package com.studioprojectx.features.tasks.form.model

data class TaskFormUIState(
    val title: String = "",
    val description: String = "",
    val topAppBarTitle: String = "",
    val onTitleChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val isDeleteEnabled: Boolean = false,
)
