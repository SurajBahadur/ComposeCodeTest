package com.example.demo.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.ui.home.model.MedicineData

@Composable
fun DetailScreen(medicineData: MedicineData) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .background(Color.Gray)
            .padding(8.dp)
            .wrapContentSize()
    ) {
        Text(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .fillMaxWidth(),
            text = "Name: ${medicineData.name}",
            style = TextStyle(fontSize = 16.sp),
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .fillMaxWidth(),
            text = "Strength: ${medicineData.strength}",
            style = TextStyle(fontSize = 14.sp),
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .fillMaxWidth(),
            text = "Dose: ${medicineData.dose}",
            style = TextStyle(fontSize = 14.sp),
            textAlign = TextAlign.Start
        )
    }
}
