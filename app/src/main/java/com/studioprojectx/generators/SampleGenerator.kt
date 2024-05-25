package com.studioprojectx.generators

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.studioprojectx.models.Task
import kotlin.random.Random

fun generateRandomTasks(
    amountTasks: Int = 1
) = List(amountTasks) {
    val index = it + 1
    Task(
        title = generateLoremIpsum(index),
        isDone = index.mod(2) == 0
    )
}

fun generateLoremIpsum(
    amountWords: Int = 1
) = LoremIpsum(
    Random.nextInt(1, if (amountWords > 1) amountWords else 2)
).values.first()