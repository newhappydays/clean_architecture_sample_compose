package io.linkey.app.android.domain.usecase.todo

import io.linkey.app.android.domain.entity.todo.Todo
import io.linkey.app.android.domain.repository.TodoRepository
import javax.inject.Inject

class InsertTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend fun invoke(todo : Todo) = todoRepository.insert(todo)
}