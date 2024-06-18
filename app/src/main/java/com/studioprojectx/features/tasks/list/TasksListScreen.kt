package com.studioprojectx.features.tasks.list

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.studioprojectx.features.tasks.list.model.TasksListUIState
import com.studioprojectx.features.tasks.model.Task
import com.studioprojectx.generators.generateRandomTasks
import com.studioprojectx.ui.theme.StudioProjectXTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TasksListScreen(
    uiState: TasksListUIState,
    modifier: Modifier = Modifier,
    onNewTaskClick: () -> Unit = {},
    onTaskClick: (Task) -> Unit = {},
    onExitToAppClick: () -> Unit = {}
) {
    Column {
        val title = if (uiState.user != null) {
            " ${uiState.user}"
        } else {
            "SPX App"
        }

        TopAppBar(
            title = { Text(text = title) },
            actions = {
                var isSearchTextFieldEnabled by remember {
                    mutableStateOf(false)
                }
                var text by remember {
                    mutableStateOf("")
                }
                AnimatedVisibility(visible = isSearchTextFieldEnabled) {
                    Icon(
                        tint = Color.Gray,
                        imageVector = Icons.Filled.Close,
                        contentDescription = "ícone para fechar campo de texto de busca",
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                isSearchTextFieldEnabled = false
                                text = ""
                            }
                            .padding(8.dp),
                    )
                }
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    }, modifier.fillMaxWidth(
                        animateFloatAsState(
                            targetValue = if (isSearchTextFieldEnabled) 1f else 0f,
                            label = "basic text field width"
                        ).value
                    ),
                    decorationBox = { innerTextField ->
                        if (text.isEmpty()) {
                            Text(
                                text = "O que você busca?",
                                style = TextStyle(
                                    color = Color.Gray.copy(alpha = 0.5f),
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 18.sp
                                )
                            )
                        }
                        innerTextField()
                    },
                    textStyle = TextStyle.Default.copy(
                        fontSize = 18.sp
                    )
                )
                AnimatedVisibility(visible = !isSearchTextFieldEnabled) {
                    Row {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "ícone de busca",
                            Modifier
                                .clip(CircleShape)
                                .clickable { isSearchTextFieldEnabled = true }
                                .padding(8.dp),
                        )
                        Icon(
                            Icons.Filled.ExitToApp,
                            contentDescription = "ícone sair do app",
                            Modifier
                                .clip(CircleShape)
                                .clickable { onExitToAppClick() }
                                .padding(8.dp),
                        )
                    }
                }

            })
        Box(modifier) {
            ExtendedFloatingActionButton(
                onClick = onNewTaskClick,
                Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
                    .zIndex(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add new task icon")
                    Text(text = "")
                }
            }
            LazyColumn(Modifier.fillMaxSize()) {
                items(uiState.tasks) { task ->
                    var showDescription by remember {
                        mutableStateOf(false)
                    }
                    Row(
                        Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth()
                            .combinedClickable(onClick = {
                                showDescription = !showDescription
                            }, onLongClick = {
                                onTaskClick(task)
                            })
                    ) {
                        Box(
                            Modifier
                                .padding(vertical = 16.dp, horizontal = 8.dp)
                                .size(30.dp)
                                .border(
                                    border = BorderStroke(1.dp, color = Color.LightGray),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clip(shape = RoundedCornerShape(8.dp))
                                .clickable {
                                    Log.i("TasksListScreen", "$task")
                                    uiState.onTaskDoneChange(task)
                                }) {
                            if (task.isDone) {
                                Icon(
                                    Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    Modifier.size(30.dp),
                                    tint = Color.Magenta
                                )
                            }
                        }
                        Column(
                            Modifier.padding(16.dp),
                        ) {

                            if (uiState.tasks.isEmpty()) {
                                Text(text = "Crie sua primeira tarefa")

                                EmptyState()

                            } else {
                                Text(
                                    modifier = Modifier.padding(top = 4.dp),
                                    text = task.title, style = TextStyle.Default.copy(
                                        fontSize = 16.sp, fontWeight = FontWeight.Bold
                                    ), overflow = TextOverflow.Ellipsis, maxLines = 3
                                )
                                task.description?.let { description ->
                                    AnimatedVisibility(
                                        visible = showDescription && description.isNotBlank()
                                    ) {
                                        Column {
                                            Text(
                                                modifier = Modifier.padding(vertical = 10.dp),
                                                text = description,
                                                style = TextStyle.Default.copy(fontSize = 16.sp),
                                                overflow = TextOverflow.Ellipsis,
                                                maxLines = 3
                                            )
                                            Text(
                                                text = task.observations ?: "",
                                                style = TextStyle.Default.copy(fontSize = 16.sp),
                                                overflow = TextOverflow.Ellipsis,
                                                maxLines = 3
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (uiState.tasks.isEmpty()) {
                EmptyState()
            }

        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .border(
                width = 1.dp,
                brush = Brush.radialGradient(
                    colors = listOf(Color.Blue, Color.Gray, Color.Transparent, Color.LightGray),
                    radius = 50f
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
            .background(color = Color.LightGray)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            imageVector = Icons.Filled.AddTask,
            contentDescription = "ícone de busca",
            modifier =  Modifier
                .size(width = 100.dp, height = 100.dp)
                .clip(CircleShape)
                .padding(8.dp),
            tint = Color.DarkGray
        )

        Text(
            textAlign = TextAlign.Center,
            text = "Nenhuma tarefa registrada",
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TasksListScreenPreview() {
    StudioProjectXTheme {
        TasksListScreen(
            uiState = TasksListUIState(
                tasks = generateRandomTasks(5)
            )
        )
    }
}