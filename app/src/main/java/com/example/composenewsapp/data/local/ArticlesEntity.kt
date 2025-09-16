package com.example.composenewsapp.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composenewsapp.domain.model.Source
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "ArticlesEntity")
data class ArticlesEntity(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val id: String,
    val name: String,
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String
):Parcelable