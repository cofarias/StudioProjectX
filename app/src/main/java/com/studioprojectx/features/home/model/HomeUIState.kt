package com.studioprojectx.features.home.model

data class HomeUIState(
    val title: String = "",
    val description: String = "",
    val observations: String = "",
    val topAppBarTitle: String = "",
    val onTitleChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onObservationsChange: (String) -> Unit = {},
    val isDeleteEnabled: Boolean = false,
)
