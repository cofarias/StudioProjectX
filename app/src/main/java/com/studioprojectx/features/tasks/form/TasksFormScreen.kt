package com.studioprojectx.features.tasks.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studioprojectx.features.tasks.form.model.TaskFormUIState
import com.studioprojectx.ui.theme.StudioProjectXTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(
    uiState: TaskFormUIState,
    modifier: Modifier = Modifier,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Column(modifier) {
        val topAppBarTitle = uiState.topAppBarTitle
        val deleteEnabled = uiState.isDeleteEnabled
        TopAppBar(
            title = {
                Text(
                    text = topAppBarTitle,
                    fontSize = 20.sp,
                )
            },
            actions = {
                if (deleteEnabled) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Delete task icon",
                        Modifier
                            .clip(CircleShape)
                            .clickable {
                                onDeleteClick()
                            }
                            .padding(4.dp)
                    )
                }
                Icon(
                    Icons.Filled.Done,
                    contentDescription = "Save task icon",
                    Modifier
                        .clip(CircleShape)
                        .clickable {
                            onSaveClick()
                        }
                        .padding(4.dp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
        )
        Spacer(modifier = Modifier.size(8.dp))
        val title = uiState.title
        val description = uiState.description
        val observations = uiState.observations
        val titleFontStyle = TextStyle.Default.copy(fontSize = 24.sp)
        val descriptionFontStyle = TextStyle.Default.copy(fontSize = 18.sp)
        val observationsFontStyle = TextStyle.Default.copy(fontSize = 18.sp)

        BasicTextField(
            value = title,
            onValueChange = uiState.onTitleChange,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            decorationBox = { innerTextField ->
                if (title.isEmpty()) {
                    Text(
                        text = "Título",
                        style = titleFontStyle.copy(
                            color = Color.Gray.copy(alpha = 0.5f)
                        ),
                    )
                }
                innerTextField()
            },
            textStyle = titleFontStyle
        )

        Spacer(modifier = Modifier.size(16.dp))

        BasicTextField(
            value = description, onValueChange = uiState.onDescriptionChange,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            decorationBox = { innerTextField ->
                if (description.isEmpty()) {
                    Text(
                        text = "Descrição",
                        style = descriptionFontStyle
                            .copy(
                                color = Color.Gray.copy(alpha = 0.5f)
                            )
                    )
                }
                innerTextField()
            },
            textStyle = descriptionFontStyle
        )

        BasicTextField(
            value = observations, onValueChange = uiState.onObservationsChange,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            decorationBox = { innerTextField ->
                if (observations.isEmpty()) {
                    Text(
                        text = "Observações",
                        style = observationsFontStyle
                            .copy(
                                color = Color.Gray.copy(alpha = 0.5f)
                            )
                    )
                }
                innerTextField()
            },
            textStyle = observationsFontStyle
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TaskFormScreenPreview() {
    StudioProjectXTheme {
        TaskFormScreen(
            uiState = TaskFormUIState(
                topAppBarTitle = "Criando tarefa"
            ),
            onSaveClick = {},
            onDeleteClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskFormScreenWithEditModePreview() {
    StudioProjectXTheme {
        TaskFormScreen(
            uiState = TaskFormUIState(
                topAppBarTitle = "Editando tarefa",
                isDeleteEnabled = true
            ),
            onSaveClick = {},
            onDeleteClick = {},
        )
    }
}