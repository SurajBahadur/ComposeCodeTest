package com.example.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.example.demo.data.local.db.AppDatabase
import com.example.demo.data.local.db.dao.UserDao
import com.example.demo.data.local.db.entity.UserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Suraj Bahadur on 12/9/2024.
 */
class UserDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        // Create an in-memory database instance
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        userDao = db.userDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertUser_retrievesUser() = runBlocking {
        // Create a user entity
        val user = UserEntity(userName = "Suraj Bahadur", createdTimeStamp = 1733695914096)

        // Insert the user
        userDao.insert(user)

        // Get the user from the database
        val retrievedUser = userDao.getLoggedUser().first()

        // Assert that the retrieved user is equal to the inserted user
        assertThat(retrievedUser, equalTo(user))
    }

}