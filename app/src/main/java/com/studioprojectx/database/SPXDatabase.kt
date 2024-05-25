package com.studioprojectx.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.studioprojectx.database.dao.TaskDao
import com.studioprojectx.database.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class SPXDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}