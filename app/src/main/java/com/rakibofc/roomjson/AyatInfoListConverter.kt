package com.rakibofc.roomjson

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AyatInfoListConverter {

    @TypeConverter
    fun fromString(value: String): List<AyatInfoEntity> {
        val listType = object : TypeToken<List<AyatInfoEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<AyatInfoEntity>): String {
        return Gson().toJson(list)
    }
}

class WordInfoListConverter {

    @TypeConverter
    fun fromString(value: String): List<WordInfoEntity> {
        val listType = object : TypeToken<List<WordInfoEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<WordInfoEntity>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
