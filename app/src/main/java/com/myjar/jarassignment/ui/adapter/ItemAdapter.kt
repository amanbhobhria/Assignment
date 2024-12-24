package com.myjar.jarassignment.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myjar.jarassignment.R
import com.myjar.jarassignment.data.model.ComputerItem

class ItemAdapter(
    private val onItemClick: (ComputerItem) -> Unit
) : ListAdapter<ComputerItem, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        Log.d("ItemAdapter", "Binding item at position $position: $item")
        holder.bind(item, onItemClick)
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.item_name)
        private val itemDesc: TextView = itemView.findViewById(R.id.item_desc)




        fun bind(item: ComputerItem, onItemClick: (ComputerItem) -> Unit) {
            itemName.text = item.name
            itemDesc.text = item.data?.description ?: ""
//            itemPrice.text = item.data?.price ?: ""


            Log.d("MainActivity",item.name)
            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<ComputerItem>() {
        override fun areItemsTheSame(oldItem: ComputerItem, newItem: ComputerItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ComputerItem, newItem: ComputerItem): Boolean {
            return oldItem == newItem
        }
    }
}
