package com.yasunov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yasunov.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("SELECT * FROM note_table")
    fun getAllNotesByDate(): Flow<List<NoteEntity>>

    @Query("DELETE FROM note_table WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM note_table WHERE id = :id LIMIT 1")
    fun getNoteById(id: Int): NoteEntity

}