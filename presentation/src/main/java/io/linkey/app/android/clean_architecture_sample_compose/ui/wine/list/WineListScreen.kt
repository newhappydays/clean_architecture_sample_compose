package io.linkey.app.android.clean_architecture_sample_compose.ui.wine.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.linkey.app.android.clean_architecture_sample_compose.Route
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.CustomTopBar
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.LoadingPage
import io.linkey.app.android.clean_architecture_sample_compose.ui.wine.list.components.WineListItem
import io.linkey.app.android.clean_architecture_sample_compose.ui.wine.list.components.WineCategoryTab
import io.linkey.app.android.domain.entity.wine.WineCategory

@Composable
fun SampleListScreen(
    navController: NavController,
    viewModel : WineListViewModel = hiltViewModel()
) {
    val wineState = viewModel.wineListState.value
    val categoryState = viewModel.categoryState.value
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            CustomTopBar(navController = navController)
            categoryState.data?.let {
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(it) {
                        WineCategoryTab(
                            wine = it,
                            onItemClick = {
                                viewModel.selectCategory(it)
                            }
                        )
                    }
                }
            }
            wineState.data?.let {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(it) {
                        WineListItem(
                            sample = it,
                            onItemClick = {
                                val currentCategory = viewModel.currentSelectCategory()
                                navController.navigate(
                                    Route.WineDetailScreen.route + "/$currentCategory/${it.id}"
                                )
                            }
                        )
                    }
                }

            } ?: LoadingPage()
        }
    }
}

@Preview(name = "와인 목록 UI", showBackground = true)
@Composable
fun SampleListScreenPreview() {
    SampleListScreen(rememberNavController())
}