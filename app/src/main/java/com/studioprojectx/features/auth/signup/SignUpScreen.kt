package com.studioprojectx.features.auth.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studioprojectx.features.auth.signup.model.SignUpUIState


@Preview(showBackground = true, name = "Default")
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(
        uiState = SignUpUIState(),
        onSignUpClick = {}
    )
}

@Composable
fun SignUpScreen(
    uiState: SignUpUIState,
    onSignUpClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
    ) {
        AnimatedVisibility(visible = uiState.error != null) {
            uiState.error?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red)
                ) {
                    Text(
                        text = it,
                        Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(30.dp))
        Text(
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            text = "Cadastrar usuário",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )

        Column(
            Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = uiState.email,
                onValueChange = uiState.onEmailChange,
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25),
                label = {
                    Text(text = "Email")
                }
            )
            OutlinedTextField(
                value = uiState.password,
                onValueChange = uiState.onPasswordChange,
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25),
                label = {
                    Text(text = "Senha")
                },
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextField(
                value = uiState.confirmPassword,
                onValueChange = uiState.onConfirmPasswordChange,
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25),
                label = {
                    Text(text = "Confirmar senha")
                },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                colors = ButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.Yellow,
                    disabledContainerColor = Color(0xFF888888),
                    disabledContentColor = Color(0xFF888888)
                ),
                onClick = onSignUpClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Cadastrar")
            }
        }
    }
}