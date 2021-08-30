package com.seyeong.mylotapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.seyeong.mylotapplication.databinding.ActivitySelectLottoBinding
import java.util.*

class SelectLotto : AppCompatActivity() {
    val adapter = SelectAdapter()
    var imageList = mutableListOf<Int>()
    var randomLottoList = mutableListOf<Int>()
    var randomData: MutableList<LottoNumber> = mutableListOf()

    val binding by lazy { ActivitySelectLottoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        imageList = intent.getIntegerArrayListExtra("imageList") as MutableList<Int>
        adapter.imageList = imageList

        binding.selectButton.setOnClickListener {
            randomLotto()
        }

        binding.selectRecyclerView.adapter = adapter
        binding.selectRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun randomLotto(){
        randomLottoList.clear()
        val random = Random()
        while (true) {
            var number = random.nextInt(45) + 1
            if ( !randomLottoList.contains(number) ) {
                randomLottoList.add(number)
            }
            if (randomLottoList.size == 7) {
                break
            }
        }

        randomData.add(LottoNumber(randomLottoList.get(0), randomLottoList.get(1), randomLottoList.get(2),
            randomLottoList.get(3), randomLottoList.get(4), randomLottoList.get(5), randomLottoList.get(6)))

        adapter.lot_list = randomData
        adapter.notifyDataSetChanged()



        Log.d("태그", "randomLottoList.toString = ${randomLottoList.toString()}")


    }

}