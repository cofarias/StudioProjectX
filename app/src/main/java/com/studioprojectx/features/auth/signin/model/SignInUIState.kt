package com.studioprojectx.features.auth.signin.model

data class SignInUIState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onConfirmPasswordChange: (String) -> Unit = {},
    val error: String? = null
)
