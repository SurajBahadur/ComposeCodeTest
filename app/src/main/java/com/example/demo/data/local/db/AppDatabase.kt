package com.example.demo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demo.data.local.db.dao.UserDao
import com.example.demo.data.local.db.entity.UserEntity

/**
 * Created by Suraj Bahadur on 12/7/2024.
 */

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}