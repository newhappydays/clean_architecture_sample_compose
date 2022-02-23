package io.linkey.app.android.domain.usecase.todo

import io.linkey.app.android.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    fun invoke() = todoRepository.getAllTodo()
}