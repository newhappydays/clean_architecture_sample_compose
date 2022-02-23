package io.linkey.app.android.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.linkey.app.android.domain.entity.todo.Todo

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    val title : String,
    val content : String,
    @ColumnInfo(name = "create_at") val createAt : String
)

fun TodoEntity.toTodo() = Todo(
    uid = uid,
    title = title,
    content = content,
    createAt = createAt
)

fun Todo.toTodoEntity() = TodoEntity(
    uid = uid,
    title = title,
    content = content,
    createAt = createAt
)