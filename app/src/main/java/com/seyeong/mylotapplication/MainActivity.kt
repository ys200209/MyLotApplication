package com.seyeong.mylotapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.seyeong.mylotapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*binding.button.setOnClickListener {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val url = URL(address)
                    val urlConnection = url.openConnection() as HttpURLConnection
                    if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                        val streamReader = InputStreamReader(urlConnection.inputStream)
                        val buffered = BufferedReader(streamReader)

                        while (true) {
                            var line = buffered.readLine()?: break
                            str.append(line)
                        }
                        buffered.close()
                        urlConnection.disconnect()
                    }
                    Log.d("태그", "str = ${str}")


                    jsonArray = JSONArray("[${str}]")
                    jsonObject = jsonArray.getJSONObject(0)

                    launch(Dispatchers.Main) {
                        binding.textView.text = str.toString()
                        binding.textView2.text = "${jsonObject.getInt("drwNo")}회\n" +
                                "${jsonObject.getInt("drwtNo1")}, ${jsonObject.getInt("drwtNo2")}, " +
                                "${jsonObject.getInt("drwtNo3")}, ${jsonObject.getInt("drwtNo4")}, " +
                                "${jsonObject.getInt("drwtNo5")}, ${jsonObject.getInt("drwtNo6")} \n" +
                                "${jsonObject.getInt("firstWinamnt")}원"
                    }

                }


            } catch (e: Exception) {
                Log.d("태그", "Error : $e")
                e.printStackTrace()
            }

        }*/
    }

}
/*
suspend fun loadUrl(url: String) : String {
    val str1 = URL(url)
    val urlConnection = str1.openConnection() as HttpURLConnection
    val content = StringBuilder()
    urlConnection.requestMethod = "GET"
    if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
        val streamReader = InputStreamReader(urlConnection.inputStream)
        val buffered = BufferedReader(streamReader)

        while (true) {
            val line = buffered.readLine()?: break
            content.append(line)
        }
        Log.d("태그", "content = ${content}")
        buffered.close()
        urlConnection.disconnect()
    }
    return content.toString()
}*/