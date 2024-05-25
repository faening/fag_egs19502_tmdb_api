package com.github.faening.engsofttmdb.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenrePageData(
    @SerializedName("genres")
    val genres: List<GenreData>
) : Serializable
