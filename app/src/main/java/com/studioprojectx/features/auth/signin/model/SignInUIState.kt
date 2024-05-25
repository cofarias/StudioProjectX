package com.studioprojectx.features.auth.signin.model

data class SignInUIState(
    val email: String = "",
    val password: String = "",
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val isShowPassword: Boolean = false,
    val onTogglePasswordVisibility: () -> Unit = {},
    val isAuthenticated: Boolean = false,
    val error: String? = null
)
