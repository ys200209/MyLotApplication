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
    var lot_list = mutableListOf<Lotto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        /*for( i in 1..45) {
            var bmp : Int = context.getResources().getIdentifier("lot_"+i, "drawable", "com.seyeong.mylotapplication")
            var bitmap:Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), bmp), 38,45, false)
            imageList.add(bitmap)
        }*/

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
        holder.setLotto(lotto)  //
    }

    inner class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

        fun setLotto(lotto: Lotto) {
            Log.d("태그", "imageList.size = ${imageList.size}")
            binding.number1.setImageResource(imageList.get(5))
            binding.number2.setImageResource(imageList.get(31))
            binding.number3.setImageResource(imageList.get(17))
            binding.number4.setImageResource(imageList.get(28))
            binding.number5.setImageResource(imageList.get(34))
            binding.number6.setImageResource(imageList.get(41))
            binding.bonusNumber.setImageResource(imageList.get(36))
            Log.d("태그", "Holder.setLotto 메서드 종료")
        }

    }

}

