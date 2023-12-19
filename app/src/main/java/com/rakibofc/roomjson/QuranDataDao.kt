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
}