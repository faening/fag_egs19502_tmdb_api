package com.github.faening.engsofttmdb.api.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ReviewData(
    @SerializedName("author")
    val author: String,

    @SerializedName("author_details")
    val authorDetails: AuthorData,

    @SerializedName("content")
    val content: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String
) : Serializable

