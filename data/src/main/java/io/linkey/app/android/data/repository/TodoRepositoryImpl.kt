package io.linkey.app.android.data.repository

import io.linkey.app.android.data.room.dao.TodoDao
import io.linkey.app.android.data.room.entity.toTodo
import io.linkey.app.android.data.room.entity.toTodoEntity
import io.linkey.app.android.domain.entity.Resource
import io.linkey.app.android.domain.entity.todo.Todo
import io.linkey.app.android.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val todoDao: TodoDao
) : TodoRepository {

    override fun getAllTodo() = todoDao.getAllTodo().map { data ->
        data.map { it.toTodo() }
    }

    override fun getTodoById(uid: Long) = todoDao.getTodoById(uid).map {
        it?.let { it.toTodo() }
    }

    override suspend fun insert(todo: Todo) = todoDao.insert(todo.toTodoEntity())

    override suspend fun delete(todo: Todo) = todoDao.delete(todo.toTodoEntity())

    override suspend fun update(todo: Todo) = todoDao.update(todo.toTodoEntity())

    override suspend fun deleteAll() = todoDao.deleteAll()
}