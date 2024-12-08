package com.example.demo.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Suraj Bahadur on 12/9/2024.
 * Entity class for Medicine data
 */
@Entity("medicine_data")
data class MedicineEntity(
    /**
     * Medicine name must be unique, that's why used as primary key
     */
    @PrimaryKey
    val name: String,
    val dose: String,
    val strength: String
)
