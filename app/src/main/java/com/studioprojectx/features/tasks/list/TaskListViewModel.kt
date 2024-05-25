package com.studioprojectx.features.tasks.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studioprojectx.domainlayer.authentication.FirebaseAuthRepository
import com.studioprojectx.domainlayer.tasks.TasksRepository
import com.studioprojectx.features.tasks.list.model.TasksListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val repository: TasksRepository,
    private val firebaseAuthRepository: FirebaseAuthRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<TasksListUIState> =
        MutableStateFlow(TasksListUIState())
    val uiState
        get() = _uiState
//            .combine(repository.tasks) { uiState, tasks ->
//                uiState.copy(tasks = tasks.map { it.toTask() })
//            }.combine(firebaseAuthRepository.currentUser) { uiState, authResult ->
//                uiState.copy(user = authResult.currentUser?.email)
//            }

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(onTaskDoneChange = { task ->
                    viewModelScope.launch {
                        repository.toggleIsDone(task)
                    }
                })
            }
        }
    }

    fun signOut() {
        firebaseAuthRepository.signOut()
    }
}