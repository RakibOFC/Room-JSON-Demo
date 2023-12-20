package com.rakibofc.roomjson

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "submenus",
    /* Optional but useful as referential integrity is enforced */
    foreignKeys = [
        ForeignKey(
            entity = MenuEntity::class,
            parentColumns = ["menu_id"],
            childColumns = ["menu_id_map"],
            /* Optional within A Foreign Key */
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class SubmenuEntity(
    @PrimaryKey
    @ColumnInfo(name = "submenu_id")
    val id: Long? = null,
    @Embedded
    val submenu: Submenu,
    @ColumnInfo(name = "menu_id_map")
    val menuMapId: Long
) {
    // Bonus function that will get a Submenu from a SubmenuEntity
    fun getSubmenuFromSubmenuEntity(submenuEntity: SubmenuEntity): Submenu {
        return Submenu(name = submenuEntity.submenu.name, route = submenuEntity.submenu.route)
    }
}

data class Submenu(
    @ColumnInfo(name = "submenu_name")
    val name: String,
    @ColumnInfo(name = "route")
    val route: String
)