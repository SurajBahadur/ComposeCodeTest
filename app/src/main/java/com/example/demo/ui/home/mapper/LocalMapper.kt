package com.example.demo.ui.home.mapper

import com.example.demo.data.local.db.entity.MedicineEntity
import com.example.demo.ui.home.model.MedicineData

/**
 * Created by Suraj Bahadur on 12/9/2024.
 */
fun MedicineEntity.toMedicineData(): MedicineData {
    return MedicineData(
            name = this.name,
            dose = this.dose,
            strength = this.strength
        )
    }
