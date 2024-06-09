package com.studioprojectx.features.home

import androidx.lifecycle.ViewModel
import com.studioprojectx.domainlayer.authentication.FirebaseAuthRepository
import com.studioprojectx.features.home.model.HomeUIState
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUIState> =
        MutableStateFlow(HomeUIState())

    val uiState get() = _uiState

    fun signOut() {
        firebaseAuthRepository.signOut()
    }
}