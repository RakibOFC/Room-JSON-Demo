package com.rakibofc.roomjson

import androidx.room.Embedded
import androidx.room.Relation

data class MenuWithSubmenus(
    @Embedded
    val menuEntity: MenuEntity,
    @Relation(
        entity = SubmenuEntity::class,
        parentColumn = "menu_id",
        entityColumn = "menu_id_map"
    )
    val submenuList: List<SubmenuEntity>
)