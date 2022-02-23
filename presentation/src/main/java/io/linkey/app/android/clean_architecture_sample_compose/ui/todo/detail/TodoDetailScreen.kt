package io.linkey.app.android.clean_architecture_sample_compose.ui.todo.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.linkey.app.android.clean_architecture_sample_compose.Route
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.CustomTopBar
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.DeleteButton
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.LoadingPage
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.ModifyButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TodoDetailScreen(
    navController: NavController,
    viewModel : TodoDetailViewModel = hiltViewModel()
) {
    val state = viewModel.todoDetailState.value
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { CustomTopBar(navController = navController) },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    DeleteButton {
                        viewModel.deleteTodo()
                    }
                }
                Box(modifier = Modifier.weight(1f)) {
                    ModifyButton {
                        navController.navigate(Route.TodoEditScreen.route + "/${state.data!!.uid}")
                    }
                }
            }
        }
    ) {
        LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest {
                if (it) {
                    navController.popBackStack()
                } else {
                    scaffoldState.snackbarHostState.showSnackbar("sql 에러")
                }
            }
        }
        state.data?.let {

            Column {
                Text(text = it.title)
                Text(text = it.content)
            }


        } ?: LoadingPage()
    }
}

@Preview("Todo Detail Page", showBackground = true)
@Composable
fun TodoDetailScreenPreview() {
    TodoDetailScreen(rememberNavController())
}