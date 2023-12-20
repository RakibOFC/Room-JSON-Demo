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

    fun insertWordData(wordDetails: WordDetails) {
        quranDataDao.insertWordData(wordDetails)
    }

    fun getWordDetailsAsJsonString(): String {
        return quranDataDao.getWordDetailsAsJsonString()
    }

    fun insertMenuEntity(menuEntity: MenuEntity) {
        quranDataDao.insertMenuEntity(menuEntity)
    }

    fun insertSubmenuEntity(submenu: SubmenuEntity) {
        quranDataDao.insertSubmenuEntity(submenu)
    }

    fun insertAyatDetails(ayatDetails: AyatDetails) {
        quranDataDao.insertAyatDetails(ayatDetails)
    }

    fun insertWordDetails(wordDetailsRow: WordDetailsRow) {
        quranDataDao.insertWordDetails(wordDetailsRow)
    }

    fun getAllAyatWithWords(): List<AyatWithWords> {
        return quranDataDao.getAllAyatWithWords()
    }

    fun getALlMenusWithSubmenus(): List<MenuWithSubmenus> {
        return quranDataDao.getALlMenusWithSubmenus()
    }

    suspend fun getWordDetailsAsJson(): QuranDataJson {
        val wordDetailsList = quranDataDao.getWordDetails()

        Log.e("TAG", "WordDetailsSize: ${wordDetailsList.size}")

        val quranDataJson = QuranDataJson(
            surahNo = wordDetailsList.firstOrNull()?.surahNo ?: 0,
            ayatInfos = wordDetailsList.groupBy { it.ayatNo }

                .map { entry ->
                    val firstWordDetails = entry.value.firstOrNull()
                    AyatInfoJson(
                        ayatNo = entry.key,
                        ayatMeaning = firstWordDetails?.ayatMeaning ?: "",
                        wordInfos = entry.value.map { WordInfoJson(it.wordNo, it.word) }
                    )
                }
        )

        return quranDataJson
    }
}
