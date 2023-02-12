package com.example.cache.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class ImageModel(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "comments") val comments: Int?,
    @ColumnInfo(name = "downloads") val downloads: Int?,
    @ColumnInfo(name = "likes") val likes: Int?,
    @ColumnInfo(name = "previewURL") val previewURL: String?,
    @ColumnInfo(name = "tags") val tags: String?,
    @ColumnInfo(name = "user") val user: String?,
    @ColumnInfo(name = "recordedAt") val recordedAt: Long = System.currentTimeMillis()
)