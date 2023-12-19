package com.rakibofc.roomjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quranRepository = QuranRepository(applicationContext)

        // Insert sample data
        insertSampleData(quranRepository)

        // Get all data
        getAllQuranData(quranRepository)
    }

    private fun getAllQuranData(quranRepository: QuranRepository) = runBlocking {

        for (quranData in quranRepository.getAllQuranData()) {

            Log.e("TAG", "SurahNo: ${quranData.surahNo}")
            for (ayahInfo in quranData.ayatInfos) {
                Log.e("TAG", "AyatNo: ${ayahInfo.ayatNo}")

                for (wordInfo in ayahInfo.wordInfos) {
                    Log.e("TAG", "WordNo: ${wordInfo.wordNo}, Word: ${wordInfo.word}")
                }
            }
        }
    }

    private fun insertSampleData(quranRepository: QuranRepository) = runBlocking {
        val sampleData = getSampleData()

        for (jsonModel in sampleData) {
            quranRepository.insertQuranData(jsonModel)
        }
    }

    private fun getSampleData(): List<QuranDataEntity> {

        return listOf(
            QuranDataEntity(
                1,
                1,
                ayatInfos = listOf(
                    AyatInfoEntity(
                        ayatNo = 1,
                        wordInfos = listOf(
                            WordInfoEntity(wordNo = 1, word = "بِسۡمِ"),
                            WordInfoEntity(wordNo = 2, word = "اللّٰہِ"),
                            WordInfoEntity(wordNo = 3, word = "الرَّحۡمٰنِ"),
                            WordInfoEntity(wordNo = 4, word = "الرَّحِیۡمِ")
                        )
                    ),
                    AyatInfoEntity(
                        ayatNo = 2,
                        wordInfos = listOf(
                            WordInfoEntity(wordNo = 1, word = "اَلۡحَمۡدُ")
                        )
                    ),
                )
            )
        )
    }
}