package com.rakibofc.roomjson

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [QuranDataEntity::class, WordDetails::class, MenuEntity::class, SubmenuEntity::class, AyatDetails::class, WordDetailsRow::class],
    version = 5
)
@TypeConverters(AyatInfoListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quranDataDao(): QuranDataDao
}
