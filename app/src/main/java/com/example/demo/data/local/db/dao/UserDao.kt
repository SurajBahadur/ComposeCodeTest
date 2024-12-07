package com.example.demo.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.demo.data.local.db.entity.UserEntity


/**
 * Created by Suraj Bahadur on 12/7/2024.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)
}