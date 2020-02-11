package com.hiepsummer.draganddroprecyclerviewitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Adapter.OnStartDragListener {

    lateinit var adapter: Adapter
    lateinit var touchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = Adapter(this)
        populateListItem()

        val callback: ItemTouchHelper.Callback = ItemMoveCallbackListener(adapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        touchHelper.startDrag(viewHolder)
    }

    private fun populateListItem() {
        var users = listOf(
            "Nguyễn Văn A",
            "Nguyễn Văn B",
            "Nguyễn Văn C",
            "Nguyễn Văn D",
            "Nguyễn Văn E",
            "Nguyễn Văn F",
            "Nguyễn Văn G",
            "Nguyễn Văn H",
            "Nguyễn Văn I",
            "Nguyễn Văn K",
            "Nguyễn Văn L",
            "Nguyễn Văn M"
        )
        adapter.setUsers(users)
    }
}
