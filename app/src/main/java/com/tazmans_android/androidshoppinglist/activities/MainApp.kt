package com.tazmans_android.androidshoppinglist.activities

import android.app.Application
import com.tazmans_android.androidshoppinglist.db.MainDataBase

class MainApp : Application() {
    val database by lazy { MainDataBase.getDataBase(this) }
}