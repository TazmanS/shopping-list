package com.tazmans_android.androidshoppinglist.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tazmans_android.androidshoppinglist.R
import com.tazmans_android.androidshoppinglist.databinding.ActivityMainBinding
import com.tazmans_android.androidshoppinglist.dialogs.NewListDialog
import com.tazmans_android.androidshoppinglist.fragments.FragmentManager
import com.tazmans_android.androidshoppinglist.fragments.NoteFragment

class MainActivity : AppCompatActivity(), NewListDialog.Listener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavListener()
    }

    private fun setBottomNavListener() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.settings -> {}
                R.id.notes -> {
                    FragmentManager.setFragment(NoteFragment.newInstance(), this)
                }
                R.id.shop_list -> {}
                R.id.new_item -> {
//                    FragmentManager.currentFrag?.onClickNew()
                    NewListDialog.showDialog(this, this)
                }
            }
            true
        }
    }

    override fun onClick(name: String) {
        Log.v("MyTag", name)
    }
}