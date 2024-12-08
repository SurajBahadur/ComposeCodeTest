package com.example.demo.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.demo.data.local.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Suraj Bahadur on 12/7/2024.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    /**
     * Query will give only one user result
     */
    @Query("SELECT * FROM user ORDER BY createdTimeStamp DESC LIMIT 1 ")
    fun getLoggedUser(): Flow<UserEntity>

    @Query("DELETE FROM user")
    fun clearUserData()
}