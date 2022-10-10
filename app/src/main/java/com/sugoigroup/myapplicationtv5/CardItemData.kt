package com.sugoigroup.myapplicationtv5

import java.io.Serializable

data class CardItemData(
    val title:String,
    val content: String,
    val imageUrl: String,
    val videoUrl: String? = null,
): Serializable
