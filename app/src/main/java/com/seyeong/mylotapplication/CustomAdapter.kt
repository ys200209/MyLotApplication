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

class CustomAdapter(val context: Context): RecyclerView.Adapter<CustomAdapter.Holder>()  {
    val imageList = MainActivity().imageList
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
            binding.number1.setImageBitmap(imageList.get(0))
            binding.number2.setImageBitmap(imageList.get(1))
            binding.number3.setImageBitmap(imageList.get(2))
            binding.number4.setImageBitmap(imageList.get(3))
            binding.number5.setImageBitmap(imageList.get(4))
            binding.number6.setImageBitmap(imageList.get(5))
            binding.bonusNumber.setImageBitmap(imageList.get(6))
            Log.d("태그", "Holder.setLotto 메서드 종료")
        }

    }

}

