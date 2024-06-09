package com.studioprojectx.features.tasks.form.model

data class TaskFormUIState(
    val title: String = "",
    val description: String = "",
    val observations: String = "",
    val topAppBarTitle: String = "",
    val onTitleChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onObservationsChange: (String) -> Unit = {},
    val isDeleteEnabled: Boolean = false,
)
