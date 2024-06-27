package com.studioprojectx.features.home.model

sealed interface HomeEvent {
    data object AddProduct : HomeEvent
    data object GetProducts : HomeEvent
    data object ClearFields : HomeEvent
}