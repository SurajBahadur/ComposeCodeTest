package com.example.demo.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.demo.data.local.db.entity.MedicineEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Suraj Bahadur on 12/9/2024.
 * Medicine data dao interface
 */
@Dao
interface MedicineDao {

    /**
     * Insert the medicine into local db
     * Note: it store only the unique data.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicines(medicines: ArrayList<MedicineEntity>)

    /**
     * Query will give only one user result
     */
    @Query("SELECT * FROM medicine_data")
    fun getMedicines(): Flow<List<MedicineEntity>>

}