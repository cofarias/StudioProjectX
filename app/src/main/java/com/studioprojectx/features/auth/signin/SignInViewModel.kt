package com.studioprojectx.features.auth.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import com.studioprojectx.domainlayer.authentication.FirebaseAuthRepository
import com.studioprojectx.features.auth.signin.model.SignInUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(SignInUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onEmailChange = { email ->
                    _uiState.update { it.copy(email = email) }
                },
                onPasswordChange = { password ->
                    _uiState.update { it.copy(email = password) }
                },
                onTogglePasswordVisibility = {
                    _uiState.update {
                        it.copy(isShowPassword = !_uiState.value.isShowPassword)
                    }
                }
            )
        }
    }

     suspend fun signIn() {
        try {
            firebaseAuthRepository.signIn(
                email = _uiState.value.email,
                password = _uiState.value.password
            )

        } catch (exception: Exception) {
            Log.e("SignInViewModel", "signIn: ", exception)
            _uiState.update {
                it.copy(error = "Erro ao fazer o login")
            }

            delay(3000)

            _uiState.update {
                it.copy(error = null)
            }
        }
    }
}