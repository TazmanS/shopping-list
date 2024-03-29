package com.tazmans_android.androidshoppinglist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tazmans_android.androidshoppinglist.entities.NoteItem
import com.tazmans_android.androidshoppinglist.entities.ShopListNameItem
import kotlinx.coroutines.flow.Flow

// data access object
// Flow позволяет динамически обновлять таблицу после добавление чеголибо

@Dao
interface Dao {
    //    NOTES
    @Query("SELECT * FROM note_list")
    fun getAllNotes(): Flow<List<NoteItem>>

    @Insert
    suspend fun insertNote(note: NoteItem)

    @Query("DELETE FROM note_list WHERE id IS :id")
    suspend fun deleteNote(id: Int)

    @Update
    suspend fun updateNote(note: NoteItem)


    //    SHOPPING LIST NAMES
    @Insert
    suspend fun insertShopListName(name: ShopListNameItem)

    @Query("SELECT * FROM shopping_list_names")
    fun getAllShopListNames(): Flow<List<ShopListNameItem>>

    @Query("DELETE FROM shopping_list_names WHERE id IS :id")
    suspend fun deleteShopListName(id: Int)

    @Update
    suspend fun updateListName(shopListName: ShopListNameItem)
}