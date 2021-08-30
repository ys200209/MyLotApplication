package com.seyeong.mylotapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.seyeong.mylotapplication.databinding.ActivityShowNumberBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ShowNumber : AppCompatActivity() {
    val address = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=977"
    var str = StringBuilder()
    var jsonArray = JSONArray()
    var jsonObject = JSONObject()
    var drwNo: Int = 0
    var randomData: MutableList<LottoNumber> = mutableListOf()
    val data: MutableList<Lotto> = mutableListOf()
    val binding by lazy { ActivityShowNumberBinding.inflate(layoutInflater) }
    var adapter = CustomAdapter()
    val main_activity = MainActivity()
    var imageList = mutableListOf<Int>()
    // var intent = getIntent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        imageList = intent.getIntegerArrayListExtra("imageList") as MutableList<Int>

        loadLotto()
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

                    Log.d("태그", "randomData = ${randomData.toString()}")
                    Log.d("태그", "randomData = ${randomData.size}")

                    Log.d("태그", "(ShowNumber) imageList.size = ${imageList.size}")
                    adapter.imageList = imageList
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

}