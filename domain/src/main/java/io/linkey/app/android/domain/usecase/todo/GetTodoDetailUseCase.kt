package io.linkey.app.android.domain.usecase.todo

import io.linkey.app.android.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodoDetailUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    fun invoke(uid : Long) = todoRepository.getTodoById(uid)
}