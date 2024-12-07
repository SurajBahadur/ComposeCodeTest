package com.example.demo.ui.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.ui.home.model.MedicineData
import com.example.demo.ui.home.repository.HomeRepository
import com.example.demo.ui.home.uiState.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    init {
        fetchMedicineData()
    }

    private val _uiState = mutableStateOf<UiState>(UiState.InProgress)
    val uiState: MutableState<UiState>
        get() = _uiState

    private val _storeData = MutableStateFlow<ArrayList<MedicineData>>(arrayListOf())
    val storeData = _storeData.asStateFlow()

    /**
     * Fetches Medicine items from the Mock API
     *
     * Launches a coroutine to fetch items from the repository.
     * The operation runs on the IO dispatcher. Exceptions and responses are logged.
     *
     */
    fun fetchMedicineData() {
        viewModelScope.launch {
            homeRepository.getMedicineData()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    Log.e("checkData", " exception:  $e")
                }
                .collect {
                    val arrayListData:ArrayList<MedicineData> = arrayListOf()
                    val associatedDrugsList = mutableListOf<Map<String, String>>()
                    val root = JSONObject(it.string())
                    val problems = root.getJSONArray("problems")

                    for (i in 0 until problems.length()) {
                        val problem = problems.getJSONObject(i)

                        // Check if Diabetes exists
                        if (problem.has("Diabetes")) {
                            val diabetesArray = problem.getJSONArray("Diabetes")

                            for (j in 0 until diabetesArray.length()) {
                                val diabetes = diabetesArray.getJSONObject(j)

                                if (diabetes.has("medications")) {
                                    val medications = diabetes.getJSONArray("medications")

                                    for (k in 0 until medications.length()) {
                                        val medication = medications.getJSONObject(k)

                                        if (medication.has("medicationsClasses")) {
                                            val medicationsClasses =
                                                medication.getJSONArray("medicationsClasses")

                                            for (l in 0 until medicationsClasses.length()) {
                                                val medicationClass =
                                                    medicationsClasses.getJSONObject(l)

                                                // Parse `className`
                                                if (medicationClass.has("className")) {
                                                    val classNameArray =
                                                        medicationClass.getJSONArray("className")

                                                    for (m in 0 until classNameArray.length()) {
                                                        val className =
                                                            classNameArray.getJSONObject(m)

                                                        // Parse `associatedDrug`
                                                        if (className.has("associatedDrug")) {
                                                            val associatedDrugs =
                                                                className.getJSONArray("associatedDrug")

                                                            for (n in 0 until associatedDrugs.length()) {
                                                                val drug =
                                                                    associatedDrugs.getJSONObject(n)
                                                                associatedDrugsList.add(
                                                                    mapOf(
                                                                        "name" to drug.getString("name"),
                                                                        "dose" to drug.getString("dose"),
                                                                        "strength" to drug.getString(
                                                                            "strength"
                                                                        )
                                                                    )
                                                                )
                                                            }
                                                        }

                                                        // Parse `associatedDrug#2`
                                                        if (className.has("associatedDrug#2")) {
                                                            val associatedDrugs2 =
                                                                className.getJSONArray("associatedDrug#2")

                                                            for (n in 0 until associatedDrugs2.length()) {
                                                                val drug =
                                                                    associatedDrugs2.getJSONObject(n)
                                                                associatedDrugsList.add(
                                                                    mapOf(
                                                                        "name" to drug.getString("name"),
                                                                        "dose" to drug.getString("dose"),
                                                                        "strength" to drug.getString(
                                                                            "strength"
                                                                        )
                                                                    )
                                                                )
                                                            }
                                                        }
                                                    }
                                                }

                                                // Parse `className2`
                                                if (medicationClass.has("className2")) {
                                                    val className2Array =
                                                        medicationClass.getJSONArray("className2")

                                                    for (m in 0 until className2Array.length()) {
                                                        val className2 =
                                                            className2Array.getJSONObject(m)

                                                        // Parse `associatedDrug`
                                                        if (className2.has("associatedDrug")) {
                                                            val associatedDrugs =
                                                                className2.getJSONArray("associatedDrug")

                                                            for (n in 0 until associatedDrugs.length()) {
                                                                val drug =
                                                                    associatedDrugs.getJSONObject(n)
                                                                associatedDrugsList.add(
                                                                    mapOf(
                                                                        "name" to drug.getString("name"),
                                                                        "dose" to drug.getString("dose"),
                                                                        "strength" to drug.getString(
                                                                            "strength"
                                                                        )
                                                                    )
                                                                )
                                                            }
                                                        }

                                                        // Parse `associatedDrug#2`
                                                        if (className2.has("associatedDrug#2")) {
                                                            val associatedDrugs2 =
                                                                className2.getJSONArray("associatedDrug#2")

                                                            for (n in 0 until associatedDrugs2.length()) {
                                                                val drug =
                                                                    associatedDrugs2.getJSONObject(n)
                                                                associatedDrugsList.add(
                                                                    mapOf(
                                                                        "name" to drug.getString("name"),
                                                                        "dose" to drug.getString("dose"),
                                                                        "strength" to drug.getString(
                                                                            "strength"
                                                                        )
                                                                    )
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Print the associated drugs list
                    associatedDrugsList.forEach { drug ->
                        arrayListData.add(MedicineData(drug["name"].toString(),drug["dose"].toString(),drug["strength"].toString()))
                    }
                    _storeData.emit(arrayListData)
                    _uiState.value = UiState.StoreData
                }
        }
    }

}