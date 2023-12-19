package com.rakibofc.roomjson

import android.content.Context
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
}
