package io.linkey.app.android.clean_architecture_sample_compose.ui.todo.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.linkey.app.android.clean_architecture_sample_compose.ui.base.BaseViewModel
import io.linkey.app.android.clean_architecture_sample_compose.ui.base.UiState
import io.linkey.app.android.domain.entity.todo.Todo
import io.linkey.app.android.domain.usecase.todo.DeleteTodoUseCase
import io.linkey.app.android.domain.usecase.todo.GetTodoDetailUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoDetailViewModel @Inject constructor(
    private val getTodoDetailUseCase: GetTodoDetailUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _todoDetailState = mutableStateOf(UiState<Todo>())
    val todoDetailState : State<UiState<Todo>> = _todoDetailState

    private val _eventFlow = MutableSharedFlow<Boolean>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("todoId")?.let { todoId ->
            getTodoDetail(todoId.toLong())
        }
    }

    private fun getTodoDetail(todoId : Long) {
        getTodoDetailUseCase.invoke(todoId)
            .onEach {
                _todoDetailState.value = UiState(data = it)
            }.launchIn(viewModelScope)
    }

    fun deleteTodo() = viewModelScope.launch {
        try {
            val todo = todoDetailState.value.data!!
            deleteTodoUseCase.invoke(todo)
            _eventFlow.emit(true)

        } catch (e: Exception){
            e.printStackTrace()
            _eventFlow.emit(false)

        }
    }
}