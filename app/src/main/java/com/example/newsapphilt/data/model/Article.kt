package com.example.newsapphilt.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anushka.newsapiclient.data.model.Source
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "article_table")
data class Article(
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @PrimaryKey
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
    /*  @SerializedName("source")
      val source: Source,*/
) : Serializable