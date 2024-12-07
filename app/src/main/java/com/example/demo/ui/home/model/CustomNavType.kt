package com.example.demo.ui.home.model


import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {

    val DogType = object : NavType<MedicineData>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): MedicineData? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): MedicineData {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: MedicineData): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: MedicineData) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}