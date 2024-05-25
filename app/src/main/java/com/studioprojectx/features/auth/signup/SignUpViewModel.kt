package com.studioprojectx.features.auth.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.studioprojectx.domainlayer.authentication.FirebaseAuthRepository
import com.studioprojectx.features.auth.signup.model.SignUpUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUIState())
    val uiState = _uiState.asStateFlow()

    private val _signUpIsSuccessful = MutableSharedFlow<Boolean>()
    val signUpIsSuccessful = _signUpIsSuccessful.asSharedFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onEmailChange = { email ->
                    _uiState.update { it.copy(email = email) }
                },
                onPasswordChange = { password ->
                    _uiState.update { it.copy(email = password) }
                },
                onConfirmPasswordChange = { password ->
                    _uiState.update {
                        it.copy(confirmPassword = password)
                    }
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
            Log.e("SignUpViewModel", "signUp: ", exception)
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