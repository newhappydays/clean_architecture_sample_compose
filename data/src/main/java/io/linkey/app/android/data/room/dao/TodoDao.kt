package io.linkey.app.android.data.room.dao

import androidx.room.*
import io.linkey.app.android.data.room.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY uid DESC")
    fun getAllTodo() : Flow<List<TodoEntity>>

    @Query("SELECT * FROM todo WHERE uid = :uid")
    fun getTodoById(uid : Long) : Flow<TodoEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo : TodoEntity)

    @Delete
    suspend fun delete(todo : TodoEntity)

    @Update
    suspend fun update(todo: TodoEntity)

    @Query("DELETE FROM todo")
    suspend fun deleteAll()



}