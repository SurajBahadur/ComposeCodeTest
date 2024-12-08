package com.example.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.demo.data.local.db.AppDatabase
import com.example.demo.data.local.db.entity.MedicineEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.*

import androidx.test.espresso.matcher.ViewMatchers.assertThat

/**
 * Created by Suraj Bahadur on 12/9/2024.
 */
@RunWith(AndroidJUnit4::class)
class MedicineDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertMedicines_shouldInsertUniqueMedicines() = runBlocking {
        val medicineDao = database.medicineDao()

        // Insert a list of medicines
        val medicines = listOf(
            MedicineEntity("med_1", "Medicine 1", ""),
            MedicineEntity("med_2", "Medicine 2", ""),
            MedicineEntity(
                "med_1", "Medicine 1", ""
            ) // Duplicate
        )
        medicineDao.insertMedicines(ArrayList(medicines))

        // Verify that only unique medicines are inserted
        val result = medicineDao.getMedicines().first()
        assertThat(3, equalTo(result.size) ) // fail case
        assertThat(2, equalTo(result.size) ) // pass case
    }

}