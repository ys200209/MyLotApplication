package com.seyeong.mylotapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.seyeong.mylotapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    //val imageList = mutableListOf<Bitmap>()
    val imageList = mutableListOf<Int>()

    var menu:MutableList<String> = mutableListOf("당첨번호 확인하기", "랜덤 추첨하기", "후원하기", "롤하러 가기", "캐리하기")
    var menu_adapter = MenuAdapter()
    // var adapter = CustomAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*
        for( i in 1..45) {
            var bmp : Int = this.resources.getIdentifier("lot_"+i, "drawable", "com.seyeong.mylotapplication")
            var bitmap:Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.resources, bmp), 38,45, false)
            imageList.add(bitmap)
        }
        */

        for (i in 1..45) {
            imageList.add(this.resources.getIdentifier("lot_"+i, "drawable", "com.seyeong.mylotapplication"))
        }

        Log.d("태그", "시작...")

        val a: Int = R.drawable.lot_38



        //val lottoData: MutableList<Lotto> = loadLotto()
        Log.d("태그", "(MainActivity) imageList.size = ${imageList.size}")
        menu_adapter.imageList = imageList
        //loadLotto()

        menu_adapter.menuList = menu
        binding.menuRecyclerView.adapter = menu_adapter
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        /*binding.button.setOnClickListener {
            randomLotto()
        }*/

    }


/*

*/
}

