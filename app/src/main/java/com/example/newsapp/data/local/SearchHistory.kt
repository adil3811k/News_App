package com.example.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Search_History")
data class SearchHistory(
    val value: String,
    @PrimaryKey(true) val id:Int = 0
)
