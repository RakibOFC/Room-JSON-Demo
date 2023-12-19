package com.rakibofc.roomjson

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuranDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuranData(quranData: QuranDataEntity)

    @Query("SELECT * FROM quran_data")
    suspend fun getAllQuranData(): List<QuranDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordData(wordDetails: WordDetails)

    @Query(
        """
        SELECT '[' ||
            GROUP_CONCAT(
                '{"surah_no":' || surah_no || ',"ayat_no":' || ayat_no || ',"word_no":' || word_no || ',"word":"' || word || '"}'
            , ',') || ']' AS word_details_json
        FROM word_details
    """
    )
    fun getWordDetailsAsJsonString(): String

    @Query("SELECT * FROM word_details")
    suspend fun getWordDetails(): List<WordDetails>
}