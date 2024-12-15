package com.example.newsapp.domain

import androidx.room.TypeConverter
import com.example.newsapp.data.remot.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyCustomTypeConverter {
    val gson  = Gson()

    @TypeConverter
    fun formSourceTOJsonObject(source: Source): String{
        return gson.toJson(source)
    }

    @TypeConverter
    fun stringToSource(data: String): Source{
        val type = object : TypeToken<Source>(){}.type
        return gson.fromJson(data,type)
    }

}