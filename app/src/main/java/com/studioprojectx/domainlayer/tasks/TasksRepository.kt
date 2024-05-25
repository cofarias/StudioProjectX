package com.studioprojectx.domainlayer.tasks

import com.studioprojectx.database.dao.TaskDao
import com.studioprojectx.database.entities.TaskEntity
import com.studioprojectx.models.Task
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class TasksRepository(
    private val dao: TaskDao
) {
    val tasks get() = dao.findAll()

    suspend fun save(task: Task) = withContext(IO) {
        dao.save(task.toTaskEntity())
    }

    suspend fun toggleIsDone(task: Task) = withContext(IO){
        val entity = task.copy(isDone = !task.isDone)
            .toTaskEntity()
        dao.save(entity)
    }

    suspend fun delete(id: String) {
        dao.delete(
            TaskEntity(
                id = id,
                title = ""
            )
        )
    }

    fun findById(id: String) = dao.findById(id = id)
}

fun Task.toTaskEntity() = TaskEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    isDone = this.isDone
)
fun Task.toTask() = Task(
    id = this.id,
    title = this.title,
    description = this.description,
)