package com.seyeong.mylotapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seyeong.mylotapplication.databinding.ActivitySelectLottoBinding
import com.seyeong.mylotapplication.databinding.ItemRecyclerBinding

class SelectAdapter: RecyclerView.Adapter<SelectAdapter.Holder>() {
    var imageList = mutableListOf<Int>()
    var lot_list = mutableListOf<LottoNumber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return lot_list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val lotto = lot_list.get(position)
        holder.setLotto(lotto)
    }

    inner class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

        fun setLotto(lotto: LottoNumber) {
            binding.number1.setImageResource(imageList.get(lotto.drwtNo1 - 1))
            binding.number2.setImageResource(imageList.get(lotto.drwtNo2 - 1))
            binding.number3.setImageResource(imageList.get(lotto.drwtNo3 - 1))
            binding.number4.setImageResource(imageList.get(lotto.drwtNo4 - 1))
            binding.number5.setImageResource(imageList.get(lotto.drwtNo5 - 1))
            binding.number6.setImageResource(imageList.get(lotto.drwtNo6 - 1))
            binding.bonusNumber.setImageResource(imageList.get(lotto.bnusNo - 1))
        }

    }

}

