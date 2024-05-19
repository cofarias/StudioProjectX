package com.studioprojectx.features.tasks.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.studioprojectx.features.tasks.list.model.TasksListUIState
import com.studioprojectx.models.Task

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TasksListScreen(
    uiState: TasksListUIState,
    modifier: Modifier = Modifier,
    onNewTaskClick: () -> Unit = {},
    onTaskClick: (Task) -> Unit = {},
) {
    Column {
        Text(text = "Oie")
        Text(text = "Oie")
        Text(text = "Oie")
        Text(text = "Oie")
    }
}