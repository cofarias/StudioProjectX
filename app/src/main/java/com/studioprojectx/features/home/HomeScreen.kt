package com.studioprojectx.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.studioprojectx.ui.theme.StudioProjectXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    //uiState: HomeUIState,
    onNewTaskClick: () -> Unit = {},
    onTaskListClick: () -> Unit = {},
) {
    Column {

        ExtendedFloatingActionButton(
            onClick = onNewTaskClick,
            Modifier
                .padding(16.dp)
                .zIndex(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                //Icon(Icons.Filled.Add, contentDescription = "Add new task icon")
                Text(text = "Criar tarefa")
            }
        }
        ExtendedFloatingActionButton(
            onClick = onTaskListClick,
            Modifier
                .padding(16.dp)
                .zIndex(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
               // Icon(Icons.Filled.Add, contentDescription = "Add new task icon")
                Text(text = "Listagem das tarefas")
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