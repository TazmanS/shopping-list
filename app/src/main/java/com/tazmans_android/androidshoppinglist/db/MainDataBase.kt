package com.tazmans_android.androidshoppinglist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tazmans_android.androidshoppinglist.entities.LibraryItem
import com.tazmans_android.androidshoppinglist.entities.NoteItem
import com.tazmans_android.androidshoppinglist.entities.ShopListNameItem
import com.tazmans_android.androidshoppinglist.entities.ShoppingListItem

@Database(
    entities = [LibraryItem::class, NoteItem::class, ShoppingListItem::class, ShopListNameItem::class],
    version = 1
)
abstract class MainDataBase : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MainDataBase? = null
        fun getDataBase(context: Context): MainDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDataBase::class.java,
                    "shopping_list.db"
                ).build()
                instance
            }
        }
    }
}