package com.example.demo.ui.home.model

import kotlinx.serialization.Serializable
@Serializable
data class MedicineData(
    val name: String="",
    val dose: String="",
    val strength: String=""
)