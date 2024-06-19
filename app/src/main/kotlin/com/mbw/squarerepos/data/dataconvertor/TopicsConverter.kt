package com.mbw.squarerepos.data.dataconvertor

import androidx.room.TypeConverter

class TopicsConverter {
    @TypeConverter
    fun fromTopics(topics: List<String>?): String? {
        return topics?.joinToString(",")
    }

    @TypeConverter
    fun toTopics(data: String?): List<String>? {
        return data?.split(",")
    }
}
