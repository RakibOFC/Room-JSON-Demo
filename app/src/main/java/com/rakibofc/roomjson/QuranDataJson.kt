package com.rakibofc.roomjson

import com.google.gson.annotations.SerializedName

data class QuranDataJson(
    @SerializedName("surah_no")
    val surahNo: Int,

    @SerializedName("ayat_infos")
    val ayatInfos: List<AyatInfoJson>
)

data class AyatInfoJson(
    @SerializedName("ayat_no")
    val ayatNo: Int,

    @SerializedName("wordInfos")
    val wordInfos: List<WordInfoJson>
)

data class WordInfoJson(
    @SerializedName("word_no")
    val wordNo: Int,

    @SerializedName("word")
    val word: String
)
