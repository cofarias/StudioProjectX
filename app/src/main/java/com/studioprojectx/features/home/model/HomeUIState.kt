package com.studioprojectx.features.home.model

data class HomeUIState(
    val nameProduct: String = "",
    val nameProductChange: (String) -> Unit = {},
    val descriptionProduct: String = "",
    val descriptionProductChange: (String) -> Unit = {},
    val observationProduct: String = "",
    val observationProductChange: (String) -> Unit = {},
    val products: List<Map<String, Any>> = emptyList()
)
