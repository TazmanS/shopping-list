package com.tazmans_android.androidshoppinglist.fragments

import androidx.appcompat.app.AppCompatActivity
import com.tazmans_android.androidshoppinglist.R

object FragmentManager {
    var currentFrag: BaseFragment? = null

    fun setFragment(newFrag: BaseFragment, activity: AppCompatActivity) {
        val transition = activity.supportFragmentManager.beginTransaction()
        transition.replace(R.id.placeHolder, newFrag)
        transition.commit()
        currentFrag = newFrag
    }
}