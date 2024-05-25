package com.github.faening.engsofttmdb.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponsePageData<T>(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<T>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
) : Serializable