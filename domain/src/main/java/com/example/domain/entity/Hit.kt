package com.example.domain.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hit(
    @SerializedName("collections")
    var collections: Int? = null,
    @SerializedName("comments")
    var comments: Int?,
    @SerializedName("downloads")
    var downloads: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("imageHeight")
    var imageHeight: Int? = null,
    @SerializedName("imageSize")
    var imageSize: Int? = null,
    @SerializedName("imageWidth")
    var imageWidth: Int? = null,
    @SerializedName("largeImageURL")
    var largeImageURL: String? = null,
    @SerializedName("likes")
    var likes: Int? = null,
    @SerializedName("pageURL")
    var pageURL: String? = null,
    @SerializedName("previewHeight")
    var previewHeight: Int? = null,
    @SerializedName("previewURL")
    var previewURL: String? = null,
    @SerializedName("previewWidth")
    var previewWidth: Int? = null,
    @SerializedName("tags")
    var tags: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("user")
    var user: String? = null,
    @SerializedName("user_id")
    var userId: Int? = null,
    @SerializedName("userImageURL")
    var userImageURL: String? = null,
    @SerializedName("views")
    var views: Int? = null,
    @SerializedName("webformatHeight")
    var webformatHeight: Int? = null,
    @SerializedName("webformatURL")
    var webformatURL: String? = null,
    @SerializedName("webformatWidth")
    var webformatWidth: Int? = null
): Parcelable