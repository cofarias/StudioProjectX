package com.studioprojectx.features.tasks.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studioprojectx.domainlayer.tasks.TasksRepository
import com.studioprojectx.domainlayer.tasks.toTask
import com.studioprojectx.features.tasks.form.model.TaskFormUIState
import com.studioprojectx.features.tasks.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class TaskFormViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: TasksRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<TaskFormUIState> =
        MutableStateFlow(TaskFormUIState())
    val uiState = _uiState.asStateFlow()
    private val id: String? = savedStateHandle["taskId"]

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onTitleChange = { title ->
                    _uiState.update {
                        it.copy(title = title)
                    }
                },
                onDescriptionChange = { description ->
                    _uiState.update {
                        it.copy(description = description)
                    }
                },
                onObservationsChange = { observations ->
                    _uiState.update {
                        it.copy(observations = observations)
                    }
                },
                topAppBarTitle = "Criando uma tarefa"
            )
        }
        id?.let {
            viewModelScope.launch {
                repository.findById(id)
                    .filterNotNull()
                    .mapNotNull {
                        it.toTask()
                    }.collectLatest { task ->
                        _uiState.update { currentState ->
                            currentState.copy(
                                topAppBarTitle = "Editando tarefa",
                                title = task.title,
                                description = task.description ?: "",
                                observations = task.observations ?: "",
                                isDeleteEnabled = true
                            )
                        }
                    }
            }
        }
    }

    suspend fun save() {
        with(_uiState.value) {
            repository.save(
                Task(
                    id = id ?: UUID.randomUUID().toString(),
                    title = title,
                    description = description,
                    observations = observations
                )
            )
        }

    }

    suspend fun delete() {
        id?.let {
            repository.delete(id)
        }
    }
}