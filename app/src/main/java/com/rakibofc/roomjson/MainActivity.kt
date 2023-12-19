package com.rakibofc.roomjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        // Insert sample word data
        insertSampleWordData(quranRepository)

        // Get all word details
        getWordDetailsAsJson(quranRepository)
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

    private fun getWordDetailsAsJson(quranRepository: QuranRepository) = runBlocking {
        Log.e("TAG", "getWordDetailsAsJson: ${quranRepository.getWordDetailsAsJsonString()}")


        for (ayahInfo in quranRepository.getWordDetailsAsJson().ayatInfos) {

            Log.e("TAG", "AyahInfo: ${ayahInfo.ayatNo}")
            Log.e("TAG", "AyahMean: ${ayahInfo.ayatMeaning}")

            for (wordInfo in ayahInfo.wordInfos)
                Log.e("TAG", "WordNo: ${wordInfo.wordNo}, Word: ${wordInfo.word}")
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

    private fun insertSampleWordData(quranRepository: QuranRepository) = runBlocking {

        val wordDetailsList: MutableList<WordDetails> = mutableListOf()

        wordDetailsList.add(WordDetails(1, 1, 1, "In the Name of Allah—the Most Compassionate, Most Merciful.", 1, "Bismi"))
        wordDetailsList.add(WordDetails(2, 1, 1, "In the Name of Allah—the Most Compassionate, Most Merciful.", 2, "Allahi"))
        wordDetailsList.add(WordDetails(3, 1, 1, "In the Name of Allah—the Most Compassionate, Most Merciful.", 3, "Ar-Rahmani"))
        wordDetailsList.add(WordDetails(4, 1, 1, "In the Name of Allah—the Most Compassionate, Most Merciful.", 4, "Ar-Raahim"))
        wordDetailsList.add(WordDetails(5, 1, 2, "All praise is for Allah—Lord of all worlds,", 1, "Al hamdu"))
        wordDetailsList.add(WordDetails(6, 1, 2, "All praise is for Allah—Lord of all worlds,", 2, "lillahi"))

        wordDetailsList.add(WordDetails(7, 2, 1, "All praise is for Allah—Lord of all worlds,", 1, "Al hamdu"))
        wordDetailsList.add(WordDetails(8, 2, 1, "All praise is for Allah—Lord of all worlds,", 2, "lillahi"))
        wordDetailsList.add(WordDetails(9, 2, 2, "All praise is for Allah—Lord of all worlds,", 1, "Al hamdu"))
        wordDetailsList.add(WordDetails(10, 2, 2, "All praise is for Allah—Lord of all worlds,", 2, "lillahi"))

        for (wordDetails in wordDetailsList) {
            quranRepository.insertWordData(wordDetails)
        }
    }
}