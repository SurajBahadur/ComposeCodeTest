package com.example.demo.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    val userName: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
