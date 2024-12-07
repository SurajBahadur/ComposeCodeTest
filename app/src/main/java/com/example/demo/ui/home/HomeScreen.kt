package com.example.demo.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.ui.home.model.MedicineData
import com.example.demo.ui.home.uiState.UiState
import com.example.myapplication.R
import kotlinx.serialization.Serializable
import java.util.Calendar

@Serializable
data class MedicineDetailRoute(
    val medicineData: MedicineData
)

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel, userName: String) {

    val modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
    val data by viewModel.storeData.collectAsState()

    AnimatedContent(targetState = viewModel.uiState.value, label = "", transitionSpec = {
        fadeIn(animationSpec = tween(1000)) togetherWith fadeOut(animationSpec = tween(600))
    }) {
        when (it) {
            // Specifies the mapping between a given FreeTrialUIState and a composable function.
            UiState.StoreData -> ItemsList(data, modifier, navController, userName)
            UiState.InProgress -> ProgressLoader(modifier)
        }
    }
}

@Composable
fun ItemsList(
    data: ArrayList<MedicineData>,
    modifier: Modifier,
    navController: NavHostController,
    userName: String
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {

        item {
            Greeting(userName, modifier)
        }
        items(data.size) { itemIndex ->
            ItemUI(data[itemIndex]) {
                navController.navigate(MedicineDetailRoute(data[itemIndex]))
            }
        }
    }
}


@Composable
fun Greeting(userName: String, modifier: Modifier) {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val greetingText = when (currentHour) {
        in 5..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        in 17..20 -> "Good Evening"
        else -> "Good Night"
    }
    Column(
        modifier = modifier.padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$greetingText $userName")
    }
}

@Composable
fun ProgressLoader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(48.dp),
            color = colorResource(id = R.color.dark),
            trackColor = colorResource(id = R.color.light),
            strokeWidth = 4.dp
        )
    }
}

@Composable
fun ItemUI(medicineData: MedicineData, onItemClick: (MedicineData) -> Unit) {
    Column(modifier = Modifier
        .padding(8.dp)
        .background(Color.Gray)
        .padding(8.dp)
        .clickable { onItemClick(medicineData) }) {
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FreeTrialUIPreview() {
    ItemUI(MedicineData()) {

    }
}
