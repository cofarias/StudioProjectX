package com.studioprojectx.features.home

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
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studioprojectx.ui.theme.StudioProjectXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    //uiState: HomeUIState,
    onNewTaskClick: () -> Unit = {},
    onTaskListClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(16.dp)
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
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .height(100.dp)
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

    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    StudioProjectXTheme {
        HomeScreen(
           // uiState = HomeUIState(),
        )
    }
}