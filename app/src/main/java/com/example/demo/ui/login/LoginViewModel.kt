package com.example.demo.ui.login

import androidx.lifecycle.ViewModel
import com.example.demo.data.local.db.entity.UserEntity
import com.example.demo.ui.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Suraj Bahadur on 12/7/2024.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
   private val repository: HomeRepository
) : ViewModel() {

    fun insertUser(userName: String) {
        if (userName.isNotEmpty()) {
            repository.insertUser(UserEntity(userName = userName))
        }
    }

}