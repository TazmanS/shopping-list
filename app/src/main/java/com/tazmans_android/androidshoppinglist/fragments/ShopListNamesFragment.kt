package com.tazmans_android.androidshoppinglist.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tazmans_android.androidshoppinglist.activities.MainApp
import com.tazmans_android.androidshoppinglist.activities.ShopListActivity
import com.tazmans_android.androidshoppinglist.databinding.FragmentShopListNamesBinding
import com.tazmans_android.androidshoppinglist.db.MainViewModel
import com.tazmans_android.androidshoppinglist.db.ShopListNameAdapter
import com.tazmans_android.androidshoppinglist.dialogs.DeleteDialog
import com.tazmans_android.androidshoppinglist.dialogs.NewListDialog
import com.tazmans_android.androidshoppinglist.entities.ShopListNameItem
import com.tazmans_android.androidshoppinglist.utils.TimeManager

class ShopListNamesFragment : BaseFragment(), ShopListNameAdapter.Listener {
    private lateinit var binding: FragmentShopListNamesBinding
    private lateinit var adapter: ShopListNameAdapter

    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }

    override fun onClickNew() {
        NewListDialog.showDialog(activity as AppCompatActivity, object : NewListDialog.Listener {
            override fun onClick(name: String) {
                val shopListName = ShopListNameItem(
                    null,
                    name,
                    TimeManager.getCurrentTime(),
                    0,
                    0,
                    ""
                )
                mainViewModel.insertShopListName(shopListName)
            }
        }, "")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopListNamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        observer()
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = ShopListNameAdapter(this@ShopListNamesFragment)
        rcView.adapter = adapter
    }

    private fun observer() {
        mainViewModel.allShopListNames.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ShopListNamesFragment()
    }

    override fun deleteItem(id: Int) =
        DeleteDialog.showDialog(context as AppCompatActivity, object : DeleteDialog.Listener {
            override fun onClick() {
                mainViewModel.deleteShopListName(id)
            }

        })

    override fun editItem(shopListName: ShopListNameItem) {
        NewListDialog.showDialog(activity as AppCompatActivity, object : NewListDialog.Listener {
            override fun onClick(name: String) {
                mainViewModel.updateListName(shopListName.copy(name = name))
            }
        }, shopListName.name)
    }

    override fun onClickItem(shopListName: ShopListNameItem) {
        val i = Intent(activity, ShopListActivity::class.java).apply {
            putExtra(ShopListActivity.SHOP_LIST_NAME, shopListName)
        }
        startActivity(i)
    }
}