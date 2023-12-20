package com.rakibofc.roomjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var quranRepository: QuranRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quranRepository = QuranRepository(applicationContext)

        // Surah details: Ayats and Words
        surahDetails()

        /*// Insert sample data
        insertSampleData()

        // Get all data
        getAllQuranData()

        // Insert sample word data
        insertSampleWordData()

        // Get all word details
        getWordDetailsAsJson()

        // insert menu data for test
        menuData()*/
    }

    private fun surahDetails() {

        /*quranRepository.insertAyatDetails(AyatDetails(1, 1, 1))
        quranRepository.insertAyatDetails(AyatDetails(2, 1, 2))

        quranRepository.insertWordDetails(WordDetailsRow(1, 1, 1, 1, "Bismi"))
        quranRepository.insertWordDetails(WordDetailsRow(2, 1, 1, 2, "Allahi"))
        quranRepository.insertWordDetails(WordDetailsRow(3, 1, 1, 3, "Ar-Rahmani"))
        quranRepository.insertWordDetails(WordDetailsRow(4, 1, 1, 4, "Ar-Raahim"))

        quranRepository.insertWordDetails(WordDetailsRow(5, 1, 2, 1, "Alhamdu"))
        quranRepository.insertWordDetails(WordDetailsRow(6, 1, 2, 2, "lillahi"))*/

        for (ayatDetails in quranRepository.getAllAyatWithWords()) {

            Log.e("TAG", "surahDetails: ${ayatDetails.ayatDetails.ayatNo}")

            for (wordDetails in ayatDetails.wordDetailsLis) {

                Log.e("TAG", "WordNo: ${wordDetails.wordNo}, Word: ${wordDetails.word}")
            }
        }
    }

    /**
     *  generate some test data
     */
    private fun menuData() {

        /*
        quranRepository.insertMenuEntity(MenuEntity(name = "menu1", type = "type1"))
        quranRepository.insertMenuEntity(MenuEntity(name = "menu2", type = "type2"))

        quranRepository.insertSubmenuEntity(SubmenuEntity(1, Submenu("SM1", "route1"), 1))
        quranRepository.insertSubmenuEntity(SubmenuEntity(2, Submenu("SM2", "route2"), 1))
        quranRepository.insertSubmenuEntity(SubmenuEntity(3, Submenu("SM3", "route3"), 1))
        quranRepository.insertSubmenuEntity(SubmenuEntity(4, Submenu("SM4", "route4"), 2))
        quranRepository.insertSubmenuEntity(SubmenuEntity(5, Submenu("SM5", "route5"), 2))
        */

        for (menu in quranRepository.getALlMenusWithSubmenus()) {

            Log.e("TAG", "menuData: ${menu.menuEntity.name}")

            for (subMenu in menu.submenuList) {
                Log.e("TAG", "menuData: ${subMenu.submenu.name}")
            }
        }
    }

    private fun getAllQuranData() = runBlocking {

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

    private fun getWordDetailsAsJson() = runBlocking {
        Log.e("TAG", "getWordDetailsAsJson: ${quranRepository.getWordDetailsAsJsonString()}")


        for (ayahInfo in quranRepository.getWordDetailsAsJson().ayatInfos) {

            Log.e("TAG", "AyahInfo: ${ayahInfo.ayatNo}")
            Log.e("TAG", "AyahMean: ${ayahInfo.ayatMeaning}")

            for (wordInfo in ayahInfo.wordInfos)
                Log.e("TAG", "WordNo: ${wordInfo.wordNo}, Word: ${wordInfo.word}")
        }
    }

    private fun insertSampleData() = runBlocking {
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

    private fun insertSampleWordData() = runBlocking {

        val wordDetailsList: MutableList<WordDetails> = mutableListOf()

        wordDetailsList.add(
            WordDetails(
                1,
                1,
                1,
                "In the Name of Allah—the Most Compassionate, Most Merciful.",
                1,
                "Bismi"
            )
        )
        wordDetailsList.add(
            WordDetails(
                2,
                1,
                1,
                "In the Name of Allah—the Most Compassionate, Most Merciful.",
                2,
                "Allahi"
            )
        )
        wordDetailsList.add(
            WordDetails(
                3,
                1,
                1,
                "In the Name of Allah—the Most Compassionate, Most Merciful.",
                3,
                "Ar-Rahmani"
            )
        )
        wordDetailsList.add(
            WordDetails(
                4,
                1,
                1,
                "In the Name of Allah—the Most Compassionate, Most Merciful.",
                4,
                "Ar-Raahim"
            )
        )
        wordDetailsList.add(
            WordDetails(
                5,
                1,
                2,
                "All praise is for Allah—Lord of all worlds,",
                1,
                "Al hamdu"
            )
        )
        wordDetailsList.add(
            WordDetails(
                6,
                1,
                2,
                "All praise is for Allah—Lord of all worlds,",
                2,
                "lillahi"
            )
        )

        wordDetailsList.add(
            WordDetails(
                7,
                2,
                1,
                "All praise is for Allah—Lord of all worlds,",
                1,
                "Al hamdu"
            )
        )
        wordDetailsList.add(
            WordDetails(
                8,
                2,
                1,
                "All praise is for Allah—Lord of all worlds,",
                2,
                "lillahi"
            )
        )
        wordDetailsList.add(
            WordDetails(
                9,
                2,
                2,
                "All praise is for Allah—Lord of all worlds,",
                1,
                "Al hamdu"
            )
        )
        wordDetailsList.add(
            WordDetails(
                10,
                2,
                2,
                "All praise is for Allah—Lord of all worlds,",
                2,
                "lillahi"
            )
        )

        for (wordDetails in wordDetailsList) {
            quranRepository.insertWordData(wordDetails)
        }
    }
}