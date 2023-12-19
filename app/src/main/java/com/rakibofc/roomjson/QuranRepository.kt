package com.rakibofc.roomjson

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuranRepository(context: Context) {

    private val database = DatabaseProvider.getDatabase(context)
    private val quranDataDao = database.quranDataDao()

    suspend fun insertQuranData(quranData: QuranDataEntity) {
        withContext(Dispatchers.IO) {
            quranDataDao.insertQuranData(quranData)
        }
    }

    suspend fun getAllQuranData(): List<QuranDataEntity> {
        return withContext(Dispatchers.IO) {
            quranDataDao.getAllQuranData()
        }
    }

    suspend fun insertWordData(wordDetails: WordDetails) {
        withContext(Dispatchers.IO) {
            quranDataDao.insertWordData(wordDetails)
        }
    }

    fun getWordDetailsAsJsonString(): String {
        return quranDataDao.getWordDetailsAsJsonString()
    }

    suspend fun getWordDetailsAsJson(): QuranDataJson {
        val wordDetailsList = quranDataDao.getWordDetails()

        val quranDataJson = QuranDataJson(
            surahNo = wordDetailsList.firstOrNull()?.surahNo ?: 0,
            ayatInfos = wordDetailsList.groupBy { it.ayatNo }
                .map { it ->
                    AyatInfoJson(
                        ayatNo = it.key,
                        wordInfos = it.value.map { WordInfoJson(it.wordNo, it.word) }
                    )
                }
        )

        return quranDataJson
    }
}
