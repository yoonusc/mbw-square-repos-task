package com.mbw.squarerepos.data.dataconvertor

import androidx.room.TypeConverter
/**
 * Used to convert the list of topics from  SquareReposEntity data to json string and vice-versa .
 */
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
