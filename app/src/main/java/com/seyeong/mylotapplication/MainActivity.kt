package com.seyeong.mylotapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    val address = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=977"
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    var str = StringBuilder()
    var jsonArray = JSONArray()
    var jsonObject = JSONObject()
    var drwNo: Int = 0
    //val imageList = mutableListOf<Bitmap>()
    val imageList = mutableListOf<Int>()
    val data: MutableList<Lotto> = mutableListOf()
    var adapter = CustomAdapter()
    var randomLottoList = mutableListOf<Int>()
    var randomData: MutableList<LottoNumber> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*for( i in 1..45) {
            var bmp : Int = this.resources.getIdentifier("lot_"+i, "drawable", "com.seyeong.mylotapplication")
            var bitmap:Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.resources, bmp), 38,45, false)
            imageList.add(bitmap)
        }*/

        for (i in 1..45) {
            imageList.add(this.resources.getIdentifier("lot_"+i, "drawable", "com.seyeong.mylotapplication"))
        }

        Log.d("태그", "시작...")

        val a: Int = R.drawable.lot_38



        //val lottoData: MutableList<Lotto> = loadLotto()
        Log.d("태그", "(MainActivity) imageList.size = ${imageList.size}")
        adapter.imageList = imageList
        loadLotto()

        binding.button.setOnClickListener {
            randomLotto()
        }

    }

    fun loadLotto() {

        try {
            CoroutineScope(Dispatchers.IO).launch {
                val url = URL(address)
                val urlConnection = url.openConnection() as HttpURLConnection
                if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    val streamReader = InputStreamReader(urlConnection.inputStream)
                    val buffered = BufferedReader(streamReader)

                    while (true) {
                        var line = buffered.readLine() ?: break
                        str.append(line)
                    }
                    buffered.close()
                    urlConnection.disconnect()

                }
                Log.d("태그", "str = ${str}")

                launch(Dispatchers.Main) {
                    Log.d("태그", "data.add( Lotto(...) )")

                    jsonArray = JSONArray("[${str}]")
                    jsonObject = jsonArray.getJSONObject(0)
                    randomData.add(
                        LottoNumber(
                            jsonObject.getInt("drwtNo1"),
                            jsonObject.getInt("drwtNo2"),
                            jsonObject.getInt("drwtNo3"),
                            jsonObject.getInt("drwtNo4"),
                            jsonObject.getInt("drwtNo5"),
                            jsonObject.getInt("drwtNo6"),
                            jsonObject.getInt("bnusNo")
                        )
                    )
                    /*data.add(
                        Lotto(
                            jsonObject.getString("returnValue"),
                            jsonObject.getString("drwNoDate"),
                            jsonObject.getInt("firstPrzwnerCo"),
                            jsonObject.getLong("firstWinamnt"),
                            jsonObject.getInt("drwNo"),
                            jsonObject.getInt("drwtNo1"),
                            jsonObject.getInt("drwtNo2"),
                            jsonObject.getInt("drwtNo3"),
                            jsonObject.getInt("drwtNo4"),
                            jsonObject.getInt("drwtNo5"),
                            jsonObject.getInt("drwtNo6"),
                            jsonObject.getInt("bnusNo")
                        )
                    )*/

                    Log.d("태그", "data = ${data.toString()}")
                    Log.d("태그", "data = ${data.size}")


                    adapter.lot_list = randomData

                    Log.d("태그", "adapter 생성.")
                    binding.recyclerView.adapter = adapter
                    Log.d("태그", "어댑터 연결.")
                    binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
                    Log.d("태그", "LayoutManager 설정.")

                }
            }
        } catch (e: Exception) {
            Log.d("태그", "Error! : ${e.message}")
            e.printStackTrace()
        }

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
        adapter.notifyDataSetChanged()

        Log.d("태그", "randomLottoList.toString = ${randomLottoList.toString()}")
        binding.random1.setImageResource(imageList.get(randomLottoList.get(0)-1))
        binding.random2.setImageResource(imageList.get(randomLottoList.get(1)-1))
        binding.random3.setImageResource(imageList.get(randomLottoList.get(2)-1))
        binding.random4.setImageResource(imageList.get(randomLottoList.get(3)-1))
        binding.random5.setImageResource(imageList.get(randomLottoList.get(4)-1))
        binding.random6.setImageResource(imageList.get(randomLottoList.get(5)-1))
        binding.bonusRandom.setImageResource(imageList.get(randomLottoList.get(6)-1))
    }

}

