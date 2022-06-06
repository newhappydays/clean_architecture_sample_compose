package io.linkey.app.android.clean_architecture_sample_compose.ui.todo.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.linkey.app.android.clean_architecture_sample_compose.ui.base.BaseViewModel
import io.linkey.app.android.clean_architecture_sample_compose.ui.base.UiState
import io.linkey.app.android.domain.entity.todo.Todo
import io.linkey.app.android.domain.usecase.todo.GetTodosUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase
) : ViewModel() {

    private val _todoDataState = mutableStateOf(UiState<List<Todo>>())
    val todoDataState : State<UiState<List<Todo>>> = _todoDataState

    init {
        getTodoData()
    }

    private fun getTodoData() {
        getTodosUseCase.invoke()
            .onEach {
                _todoDataState.value = UiState(data = it)
            }.launchIn(viewModelScope)
    }

}