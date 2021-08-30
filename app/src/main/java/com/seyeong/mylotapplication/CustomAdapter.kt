package com.seyeong.mylotapplication

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.pm.PackageInfoCompat
import androidx.recyclerview.widget.RecyclerView
import com.seyeong.mylotapplication.databinding.ItemRecyclerBinding

class CustomAdapter(): RecyclerView.Adapter<CustomAdapter.Holder>()  {
    var imageList = mutableListOf<Int>()
    // var lot_list = mutableListOf<Lotto>()
    var lot_list = mutableListOf<LottoNumber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        Log.d("태그", "(onCreateViewHolder) 종료.")

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return lot_list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val lotto = lot_list.get(position) // 아직 아무것도 안넣었는데 뭔 .get ?

        holder

        Log.d("태그", "(onBindViewHolder) setLotto 메서드 호출.")
        holder.setLotto(lotto)
    }

    inner class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

        fun setLotto(lotto: LottoNumber) {
            Log.d("태그", "(CustomAdapter) imageList.size = ${imageList.size}")
            // 977 회차 로또 번호
            binding.number1.setImageResource(imageList.get(lotto.drwtNo1-1))
            binding.number2.setImageResource(imageList.get(lotto.drwtNo2-1))
            binding.number3.setImageResource(imageList.get(lotto.drwtNo3-1))
            binding.number4.setImageResource(imageList.get(lotto.drwtNo4-1))
            binding.number5.setImageResource(imageList.get(lotto.drwtNo5-1))
            binding.number6.setImageResource(imageList.get(lotto.drwtNo6-1))
            binding.bonusNumber.setImageResource(imageList.get(lotto.bnusNo-1))
            Log.d("태그", "Holder.setLotto 메서드 종료")
        }

    }

}

