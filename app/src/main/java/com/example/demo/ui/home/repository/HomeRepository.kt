package com.example.demo.ui.home.repository

import com.example.demo.data.api.NetworkHelper
import com.example.demo.data.api.NetworkService
import com.example.demo.data.local.db.AppDatabase
import com.example.demo.data.local.db.dao.MedicineDao
import com.example.demo.data.local.db.dao.UserDao
import com.example.demo.data.local.db.entity.MedicineEntity
import com.example.demo.data.local.db.entity.UserEntity
import com.example.demo.ui.home.model.MedicineData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val apiService: NetworkService,
    private val db: AppDatabase
) : NetworkHelper {

    private var userDao: UserDao = db.userDao()
    private var medicineDao: MedicineDao = db.medicineDao()

    /**
     * Retrieves a flow of Medicine items from the Mock API.
     *
     * This method fetches medicine items from the Mock API.
     * It returns a Flow of ResponseBody.
     * which can be collected asynchronously.
     *
     * @return A Flow emitting the ResponseBody for the specified data.
     */
    override suspend fun getMedicineData(): Flow<ResponseBody> =
        flow {
            emit(apiService.getMedicineData())
        }

    /**
     * Insert the user into local db
     * @param user hold use information
     */
    fun insertUser(user: UserEntity) {
        userDao.insert(user)
    }

    /**
     * Fetch the logged detail
     * @return user information
     */
    fun getUserName(): Flow<UserEntity> {
        return userDao.getLoggedUser()
    }

    /**
     * Clear the table
     */
    fun clearUserData() {
        userDao.clearUserData()
    }

    /**
     * Insert the medicine into local db
     * @param medicines list of medicine
     */
    fun insertMedicines(medicines: ArrayList<MedicineEntity>) {
        medicineDao.insertMedicines(medicines)
    }

    /**
     * Fetch the medicine
     * @return medicine information list
     */
    fun getMedicines(): Flow<List<MedicineEntity>> {
        return medicineDao.getMedicines()
    }


}