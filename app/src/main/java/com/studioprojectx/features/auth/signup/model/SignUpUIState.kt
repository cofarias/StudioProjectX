package com.studioprojectx.features.auth.signup.model

data class SignUpUIState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onConfirmPasswordChange: (String) -> Unit = {},
    val error: String? = null
)
