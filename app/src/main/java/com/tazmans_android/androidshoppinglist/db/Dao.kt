package com.tazmans_android.androidshoppinglist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tazmans_android.androidshoppinglist.entities.NoteItem
import kotlinx.coroutines.flow.Flow

// data access object
// Flow позволяет динамически обновлять таблицу после добавление чеголибо

@Dao
interface Dao {
    @Query("SELECT * FROM note_list")
    fun getAllNotes(): Flow<List<NoteItem>>

    @Insert
    suspend fun insertNote(note: NoteItem)

    @Query("DELETE FROM note_list WHERE id IS :id")
    suspend fun deleteNote(id: Int)
}