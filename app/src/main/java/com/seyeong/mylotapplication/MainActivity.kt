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

class MainActivity : AppCompatActivity() {
    val address = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=977"
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    var str = StringBuilder()
    var jsonArray = JSONArray()
    var jsonObject = JSONObject()
    var drwNo: Int = 0
    val imageList = mutableListOf<Bitmap>()
    var lottoData: Deferred<MutableList<Lotto>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        for( i in 1..45) {
            var bmp : Int = this.resources.getIdentifier("lot_"+i, "drawable", "com.seyeong.mylotapplication")
            var bitmap:Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.resources, bmp), 38,45, false)
            imageList.add(bitmap)
        }

        Log.d("태그", "시작...")
        var adapter = CustomAdapter(this)


        CoroutineScope(Dispatchers.Default).async {
            lottoData = async {
                loadLotto()
            }
            if (lottoData.await() != null )
            adapter.lot_list = lottoData.await()

            Log.d("태그", "adapter 생성.")
            binding.recyclerView.adapter = adapter
            Log.d("태그", "어댑터 연결.")
            binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            Log.d("태그", "LayoutManager 설정.")
        }
        /*binding.button.setOnClickListener {


        }*/
    }

    fun loadLotto(): MutableList<Lotto> {
        val data: MutableList<Lotto> = mutableListOf()

        try {
            CoroutineScope(Dispatchers.Default).async {
                val url = URL(address)
                val urlConnection = url.openConnection() as HttpURLConnection
                val asyncStr = async {
                    if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                        val streamReader = InputStreamReader(urlConnection.inputStream)
                        val buffered = BufferedReader(streamReader)

                        while (true) {
                            var line = buffered.readLine() ?: break
                            str.append(line)
                        }
                        buffered.close()
                        urlConnection.disconnect()

                        str
                    } else {
                        null
                    }
                }
                Log.d("태그", "asyncStr = ${asyncStr.await()}")

                /*launch(Dispatchers.Main) {
                    /*
                    binding.textView.text = str.toString()
                    binding.textView2.text = "${jsonObject.getInt("drwNo")}회\n" +
                            "${jsonObject.getInt("drwtNo1")}, ${jsonObject.getInt("drwtNo2")}, " +
                            "${jsonObject.getInt("drwtNo3")}, ${jsonObject.getInt("drwtNo4")}, " +
                            "${jsonObject.getInt("drwtNo5")}, ${jsonObject.getInt("drwtNo6")} \n" +
                            "${jsonObject.getInt("firstWinamnt")}원"*/

                }*/

                if ( asyncStr.await() != null) {
                    Log.d("태그", "data.add( Lotto(...) )")

                    jsonArray = JSONArray("[${str}]")
                    jsonObject = jsonArray.getJSONObject(0)

                    data.add(
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
                    )

                    Log.d("태그", "data = ${data.toString()}")
                    Log.d("태그", "data = ${data.size}")
                }

            }
        } catch (e: Exception) {
            Log.d("태그", "Error : $e")
            e.printStackTrace()
        }

        return data
    }

    suspend fun jsonConnect() {

    }

}

