package com.rakibofc.roomjson

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "quran_data")
data class QuranDataEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @SerializedName("surah_no")
    var surahNo: Int,

    @TypeConverters(AyatInfoListConverter::class)
    @ColumnInfo(name = "ayat_infos")
    val ayatInfos: List<AyatInfoEntity>
)

data class AyatInfoEntity(
    @SerializedName("ayat_no")
    var ayatNo: Int,

    @TypeConverters(WordInfoListConverter::class)
    @SerializedName("wordInfos")
    var wordInfos: List<WordInfoEntity>
)

data class WordInfoEntity(
    @SerializedName("word_no")
    var wordNo: Int,

    @SerializedName("word")
    var word: String
)
