package com.rakibofc.roomjson

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_details")
class WordDetails(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo("surah_no")
    var surahNo: Int,

    @ColumnInfo("ayat_no")
    var ayatNo: Int,

    @ColumnInfo("ayat_meaning")
    var ayatMeaning: String,

    @ColumnInfo("word_no")
    var wordNo: Int,

    @ColumnInfo("word")
    var word: String
)