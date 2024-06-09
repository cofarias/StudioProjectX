package com.studioprojectx.features.tasks.model

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val observations: String? = null,
    val isDone: Boolean = false
)
