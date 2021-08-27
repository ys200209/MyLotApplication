package com.seyeong.mylotapplication

import java.util.*

data class Lotto(val returnValue: String, val drwNoDate: String, val firstPrzwnerCo:Int, val firstWinamnt: Long,
                 val drwNo: Int, val drwtNo1: Int, val drwtNo2: Int, val drwtNo3: Int,
                 val drwtNo4: Int, val drwtNo5: Int, val drwtNo6: Int, val bnusNo: Int)


/*

    "returnValue":"success" ( result )
    "firstPrzwnerCo":14, ( Person Count )
    "firstWinamnt":1669905911, ( Per Price )
    "drwNo":977,
    "drwtNo6":44,
    "drwtNo5":22,
    "drwtNo4":14,
    "drwtNo3":10,
    "drwtNo2":9,
    "drwtNo1":2,
    "bnusNo":16, ( Gold )

*/
