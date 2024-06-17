package com.studioprojectx.features.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studioprojectx.features.home.model.HomeEvent
import com.studioprojectx.features.home.model.HomeUIState
import com.studioprojectx.ui.theme.StudioProjectXTheme

@Composable
fun HomeScreen(
    uiState: HomeUIState,
    onNewTaskClick: () -> Unit = {},
    onTaskListClick: () -> Unit = {},
    onEvent: (HomeEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        val showRegisterProduct = remember { mutableStateOf(false) }

        ListOptionsFeatures(
            onNewTaskClick = onNewTaskClick,
            onTaskListClick = onTaskListClick,
            showRegisterProduct = showRegisterProduct.value,
            onShowRegisterProductClick = {
                showRegisterProduct.value = !showRegisterProduct.value
            }
        )

        if (showRegisterProduct.value) {
            CreateProduct(
                uiState = uiState,
                onEvent = onEvent
            )
        }

    }
}

@Composable
fun ListOptionsFeatures(
    onNewTaskClick: () -> Unit = {},
    onTaskListClick: () -> Unit = {},
    showRegisterProduct: Boolean = false,
    onShowRegisterProductClick: () -> Unit
) {
    Spacer(modifier = Modifier.size(30.dp))

    ElevatedCard(
        colors = CardColors(
            contentColor = Color.White,
            containerColor = Color.Black,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.LightGray
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 100.dp,
            focusedElevation = 100.dp,
            hoveredElevation = 10.dp,
            draggedElevation = 10.dp,
            disabledElevation = 10.dp
        ),
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Olá, seja bem vindo",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp),
            )
        }
    }

    Spacer(modifier = Modifier.size(30.dp))

    ExtendedFloatingActionButton(
        onClick = onNewTaskClick,
        Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "Criar tarefa"
            )
        }
    }

    Spacer(modifier = Modifier.size(30.dp))

    ExtendedFloatingActionButton(
        onClick = onTaskListClick,
        Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "Listagem das tarefas"
            )
        }
    }

    Spacer(modifier = Modifier.size(30.dp))

    ExtendedFloatingActionButton(
        onClick = onShowRegisterProductClick,
        Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "Cadastrar produto no Firebase"
            )
        }
    }

    Spacer(modifier = Modifier.size(30.dp))
}

@Composable
fun CreateProduct(
    uiState: HomeUIState,
    onEvent: (HomeEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                brush = Brush.radialGradient(
                    colors = listOf(Color.Blue, Color.Gray, Color.Transparent, Color.LightGray),
                    radius = 50f
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Text(
            modifier = Modifier.fillMaxSize(),
            fontWeight = FontWeight.Bold,
            text = "Cadastrar Produto",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome do Produto") },
            singleLine = true,
            placeholder = { Text(text = "Nome do Produto") },
            value = uiState.nameProduct,
            onValueChange = uiState.nameProductChange,
        )

        Spacer(modifier = Modifier.padding(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Descrição do Produto") },
            singleLine = true,
            placeholder = { Text(text = "Descrição do Produto") },
            value = uiState.descriptionProduct,
            onValueChange = uiState.descriptionProductChange,
        )

        Spacer(modifier = Modifier.padding(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Localized description"
                )
            },
            label = { Text("Observações") },
            singleLine = true,
            placeholder = { Text(text = "Observações") },
            value = uiState.observationProduct,
            onValueChange = uiState.observationProductChange,
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonColors(
                containerColor = Color.Magenta,
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF888888),
                disabledContentColor = Color(0xFF888888)
            ),
            content = {
                Text(
                    text = "Cadastrar produto",
                    fontWeight = FontWeight.Bold,
                )
            },
            onClick = { onEvent(HomeEvent.AddProduct) }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    StudioProjectXTheme {
        HomeScreen(
            HomeUIState(),
            onEvent = {}
        )
    }
}