package com.example.domain.entity

import com.google.gson.annotations.SerializedName


data class ImagesPixabayList(
    @SerializedName("hits")
    var hits: List<Hit?>?
)