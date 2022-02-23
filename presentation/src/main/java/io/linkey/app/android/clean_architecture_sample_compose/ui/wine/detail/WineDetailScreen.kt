package io.linkey.app.android.clean_architecture_sample_compose.ui.wine.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.CustomTopBar
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.LoadingPage

@Composable
fun SampleDetailScreen(
    navController: NavController,
    viewModel : WineDetailViewModel = hiltViewModel()
) {
    val state = viewModel.wineDetailState.value

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopBar(navController = navController)
        state.data?.let {
            Text(text = it.wine)


        } ?: LoadingPage()
    }

}

@Preview(name = "와인 상세 UI",showBackground = true)
@Composable
fun SampleDetailScreenPreview() {
    SampleDetailScreen(rememberNavController())
}