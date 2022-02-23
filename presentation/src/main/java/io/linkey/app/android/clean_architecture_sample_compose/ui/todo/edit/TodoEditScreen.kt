package io.linkey.app.android.clean_architecture_sample_compose.ui.todo.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.CustomTopBar
import io.linkey.app.android.clean_architecture_sample_compose.ui.components.SaveButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TodoEditScreen(
    navController: NavController,
    viewModel : TodoEditViewModel = hiltViewModel()
) {
    val state = viewModel.todoState.value
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = {
            SaveButton(state.title.isNotBlank() && state.content.isNotBlank()) {
                if (state.uid == 0L) {
                    viewModel.insertTodo()
                } else {
                    viewModel.updateTodo()
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
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column {

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Enter title") },
                    value = state.title,
                    onValueChange = { viewModel.setTitle(it) }
                )
                Spacer(Modifier.height(10.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    label = { Text("Enter content") },
                    value = state.content,
                    onValueChange = { viewModel.setContent(it) },
                )
            }
        }
    }
}

@Preview(name = "Todo 생성, 수정 레이아웃", showBackground = true)
@Composable
fun TodoEditScreenPreview() {
    TodoEditScreen(rememberNavController())
}
