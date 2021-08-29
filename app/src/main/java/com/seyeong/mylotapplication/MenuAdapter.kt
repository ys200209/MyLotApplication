package com.seyeong.mylotapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seyeong.mylotapplication.databinding.ItemRecyclerBinding
import com.seyeong.mylotapplication.databinding.MenuRecyclerBinding

class MenuAdapter: RecyclerView.Adapter<MenuAdapter.Holder>() {
    var menuList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = MenuRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val menu = menuList.get(position)
        holder.setMenu(menu, position)
    }

    inner class Holder(val binding: MenuRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

        fun setMenu(menu: String, pos: Int) {
            binding.menuText.text = menuList.get(pos)
        }
    }

}