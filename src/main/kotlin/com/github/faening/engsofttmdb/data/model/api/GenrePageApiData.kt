package com.github.faening.engsofttmdb.data.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenrePageApiData(
    @SerializedName("genres")
    val genres: List<GenreApiData>
) : Serializable
