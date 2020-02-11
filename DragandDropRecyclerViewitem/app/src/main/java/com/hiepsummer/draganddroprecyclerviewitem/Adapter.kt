package com.hiepsummer.draganddroprecyclerviewitem

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import java.util.*

class Adapter(private val startDragStartListener: OnStartDragListener) :
    RecyclerView.Adapter<Adapter.ItemViewHolder>(),
    ItemMoveCallbackListener.Listener {
    private var users = emptyList<String>().toMutableList()
    fun setUsers(newUsers: List<String>) {
        users.addAll(newUsers)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {
            itemView.textView.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)

        holder.itemView.imageView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                this.startDragStartListener.onStartDrag(holder)
            }
            return@setOnTouchListener true
        }
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(users, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(users, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRowSelected(itemViewHolder: ItemViewHolder) {
    }

    override fun onRowClear(itemViewHolder: ItemViewHolder) {
    }

    override fun getItemCount() = users.size
    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }
}