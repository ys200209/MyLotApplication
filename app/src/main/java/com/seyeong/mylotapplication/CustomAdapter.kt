package com.seyeong.mylotapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seyeong.mylotapplication.databinding.ItemRecyclerBinding

class CustomAdapter: RecyclerView.Adapter<Holder>()  {
    var lot_list = mutableListOf<Lotto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return lot_list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val lotto = lot_list.get(position) // 아직 아무것도 안넣었는데 뭔 .get ?
        holder.setLotto(lotto)  //
    }



}

class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

    fun setLotto(lotto: Lotto) {

    }

}