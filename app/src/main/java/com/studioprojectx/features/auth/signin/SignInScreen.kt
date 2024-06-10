package com.studioprojectx.features.auth.signin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studioprojectx.features.auth.signin.model.SignInUIState
import com.studioprojectx.ui.theme.StudioProjectXTheme

@Composable
fun SignInScreen(
    uiState: SignInUIState,
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val isError = uiState.error != null
        AnimatedVisibility(visible = isError) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.error)
            ) {
                val error = uiState.error ?: ""
                Text(
                    text = error,
                    Modifier
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.onError
                )
            }
        }
        Column(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.AllInclusive,
                contentDescription = "Ícone login",
                Modifier
                    .clip(CircleShape)
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.scrim, CircleShape)
                    .padding(10.dp),
                tint = MaterialTheme.colorScheme.surface
            )
            Spacer(modifier = Modifier.size(26.dp))

            Text(
                text = "Studio Project X",
                style = TextStyle(
                    fontSize = 30.sp
                ),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(16.dp))
            val textFieldModifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(8.dp)
            OutlinedTextField(
                value = uiState.email,
                onValueChange = uiState.onEmailChange,
                textFieldModifier,
                shape = RoundedCornerShape(25),
                leadingIcon = {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "ícone de usuário"
                    )
                },
                label = {
                    Text(text = "Email")
                }
            )
            OutlinedTextField(
                value = uiState.password,
                onValueChange = uiState.onPasswordChange,
                textFieldModifier,
                shape = RoundedCornerShape(25),
                leadingIcon = {
                    Icon(
                        Icons.Filled.Password,
                        contentDescription = "ícone de usuário"
                    )
                },
                label = {
                    Text("Senha")
                },
                trailingIcon = {
                    val trailingIconModifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            uiState.onTogglePasswordVisibility()
                        }
                    when (uiState.isShowPassword) {
                        true -> {
                            Icon(
                                Icons.Filled.Visibility,
                                contentDescription = "ícone de visível",
                                trailingIconModifier
                            )
                        }

                        else -> Icon(
                            Icons.Filled.VisibilityOff,
                            contentDescription = "ícone de não visível",
                            trailingIconModifier
                        )
                    }
                },
                visualTransformation = when (uiState.isShowPassword) {
                    false -> PasswordVisualTransformation()
                    true -> VisualTransformation.None
                }
            )
            Button(
                colors = ButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.Yellow,
                    disabledContainerColor = Color.Black,
                    disabledContentColor = Color.White
                ),
                onClick = onSignInClick,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(70.dp)
                    .padding(8.dp)
            ) {
                Text(text = "Entrar")
            }
            TextButton(
                border = BorderStroke(
                    color = Color.DarkGray,
                    width = 0.5.dp
                ),
                onClick = onSignUpClick,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(70.dp)
                    .padding(8.dp)
            ) {
                Text(text = "Cadastrar")
            }
        }
    }
}

@Preview(showBackground = true, name = "Default")
@Composable
fun SignInScreenPreview() {
    StudioProjectXTheme {
        SignInScreen(
            uiState = SignInUIState(),
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}

@Preview(showBackground = true, name = "with error")
@Composable
fun SignInScreen1Preview() {
    StudioProjectXTheme {
        SignInScreen(
            uiState = SignInUIState(
                error = "Erro ao fazer login"
            ),
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}