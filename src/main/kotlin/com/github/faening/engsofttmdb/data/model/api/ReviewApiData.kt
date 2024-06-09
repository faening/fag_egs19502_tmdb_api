package com.github.faening.engsofttmdb.data.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ReviewApiData(
    @SerializedName("author")
    val author: String,

    @SerializedName("author_details")
    val authorDetails: AuthorDetailsApiData,

    @SerializedName("content")
    val content: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("id")
    val tmdbAuthorId: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("url")
    val url: String
) : Serializable