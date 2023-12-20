package com.rakibofc.roomjson

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "menus", indices = [Index("name", "type", unique = true)])
data class MenuEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "menu_id")
    val menuId: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "type")
    val type: String
)