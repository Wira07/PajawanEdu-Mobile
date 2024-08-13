package com.wira_fkom.pajawanedumobile.data

data class ItemModel(
    val id: String,
    val name: String,
    val description: String,
    val imageResId: Int,
    var isFavorite: Boolean = false
)
