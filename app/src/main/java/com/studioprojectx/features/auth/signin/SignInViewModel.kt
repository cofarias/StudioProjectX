package com.studioprojectx.features.auth.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import com.studioprojectx.authentication.FirebaseAuthRepository
import com.studioprojectx.features.auth.signin.model.SignInUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(SignInUIState())
    val uiState = _uiState.asStateFlow()


    private val _signUpIsSuccessful = MutableSharedFlow<Boolean>()
    val signUpIsSuccessful = _signUpIsSuccessful.asSharedFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onEmailChange = { user ->
                    _uiState.update { it.copy(email = user) }
                },
                onPasswordChange = { password ->
                    _uiState.update { it.copy(email = password) }
                }
            )
        }
    }

    suspend fun signUp() {

        try {
            firebaseAuthRepository.signUp(
                _uiState.value.email,
                _uiState.value.password
            )

            _signUpIsSuccessful.emit(true)

        } catch (exception: Exception) {
            Log.e("SignInViewModel", "signUp: ", exception)
            _uiState.update {
                it.copy(error = "Erro ao cadastrar o usuário")
            }

            delay(3000)

            _uiState.update {
                it.copy(error = null)
            }
        }


    }


}