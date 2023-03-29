package com.tazmans_android.androidshoppinglist.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tazmans_android.androidshoppinglist.databinding.ActivityShopListBinding
import com.tazmans_android.androidshoppinglist.db.MainViewModel
import com.tazmans_android.androidshoppinglist.entities.ShopListNameItem

class ShopListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopListBinding
    private var shopListName: ShopListNameItem? = null
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory((applicationContext as MainApp).database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        shopListName = intent.getSerializableExtra(SHOP_LIST_NAME) as ShopListNameItem
        binding.tvTest.text = shopListName?.name

    }

    companion object {
        const val SHOP_LIST_NAME = "shop_list_name"
    }
}