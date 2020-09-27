package com.android.shopmax.local.typeConverters

import androidx.room.TypeConverter

class GenreIdsConverter {

    @TypeConverter
    fun toList(imageString: String): List<Int> {
        return imageString.split(" ").map { it.toInt() }
    }

    @TypeConverter
    fun fromList(images: List<Int>): String {

        var str: String = " "

        images.forEachIndexed { index, s ->
            str += images[index].toString().plus(" ")

        }

        return str.trim()
    }
}