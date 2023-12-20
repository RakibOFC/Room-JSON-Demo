package com.rakibofc.roomjson

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ayat_details")
data class AyatDetails(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ayat_id")
    val ayatId: Long = 0,

    @ColumnInfo(name = "surah_no")
    val surahNo: Int = 0,

    @ColumnInfo(name = "ayat_no")
    val ayatNo: Int = 0
)

@Entity(tableName = "word_details_table")
data class WordDetailsRow(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "word_id")
    val wordId: Long,

    @ColumnInfo(name = "surah_no")
    val surahNo: Int,

    @ColumnInfo(name = "ayat_no")
    val ayatNo: Int,

    @ColumnInfo(name = "word_no")
    val wordNo: Int,

    @ColumnInfo(name = "word")
    val word: String,
)