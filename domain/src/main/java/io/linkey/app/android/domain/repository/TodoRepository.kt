package io.linkey.app.android.domain.repository

import io.linkey.app.android.domain.entity.todo.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getAllTodo() : Flow<List<Todo>>

    fun getTodoById(uid : Long) : Flow<Todo?>

    suspend fun insert(todo : Todo)

    suspend fun delete(todo : Todo)

    suspend fun update(todo: Todo)

    suspend fun deleteAll()

}