package io.linkey.app.android.clean_architecture_sample_compose.ui.todo.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.linkey.app.android.clean_architecture_sample_compose.Route
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.CustomTopBar
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.EmptyPage
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.LoadingPage
import io.linkey.app.android.clean_architecture_sample_compose.ui.todo.list.components.TodoListItem
import io.linkey.app.android.clean_architecture_sample_compose.ui.wine.list.components.WineCategoryTab
import io.linkey.app.android.clean_architecture_sample_compose.ui.wine.list.components.WineListItem
import org.intellij.lang.annotations.JdkConstants

@Composable
fun TodoListScreen(
    navController: NavController,
    viewModel : TodoListViewModel = hiltViewModel()
) {
    val state = viewModel.todoDataState.value
    Scaffold(
        topBar = { CustomTopBar(navController = navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Route.TodoEditScreen.route + "/ ")
            }) {
                Icon(Icons.Filled.Add,"")
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.data == null -> LoadingPage()
                state.data.isEmpty() -> EmptyPage()
                else -> LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.data) {
                        TodoListItem(navController, it)
                    }
                }
            }
        }
    }
}