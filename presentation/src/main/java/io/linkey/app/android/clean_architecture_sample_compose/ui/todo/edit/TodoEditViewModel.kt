package io.linkey.app.android.clean_architecture_sample_compose.ui.todo.edit

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.linkey.app.android.clean_architecture_sample_compose.ui.base.BaseViewModel
import io.linkey.app.android.clean_architecture_sample_compose.ui.base.UiState
import io.linkey.app.android.domain.entity.todo.Todo
import io.linkey.app.android.domain.usecase.todo.GetTodoDetailUseCase
import io.linkey.app.android.domain.usecase.todo.GetTodosUseCase
import io.linkey.app.android.domain.usecase.todo.InsertTodoUseCase
import io.linkey.app.android.domain.usecase.todo.UpdateTodoUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoEditViewModel @Inject constructor(
    private val insertTodoUseCase: InsertTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val getTodoDetailUseCase: GetTodoDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _todoState = mutableStateOf(Todo(0,"","",""))
    val todoState : State<Todo> = _todoState

    private val _eventFlow = MutableSharedFlow<Boolean>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("todoId")?.let {
            Log.e("TAG", ": $it")
            if (it.isNotBlank()) getTodoDetail(it.toLong())
        }
    }

    fun setTitle(title : String) {
        _todoState.value = todoState.value.copy(title = title)
    }

    fun setContent(content : String) {
        _todoState.value = todoState.value.copy(content = content)
    }

    fun insertTodo() = viewModelScope.launch {
        val todo = todoState.value
        try {
            insertTodoUseCase.invoke(todo)
            _eventFlow.emit(true)

        } catch (e: Exception){
            _eventFlow.emit(false)
         }
    }

    fun updateTodo() = viewModelScope.launch {
        val todo = todoState.value
        try {
            updateTodoUseCase.invoke(todo)
            _eventFlow.emit(true)

        } catch (e: Exception){
            _eventFlow.emit(false)
        }
    }

    private fun getTodoDetail(uid : Long) {
        getTodoDetailUseCase.invoke(uid)
            .onEach {
                _todoState.value = it ?: Todo(0,"","","")
            }.launchIn(viewModelScope)
    }

}