package com.github.faening.engsofttmdb.data.model.api.genres

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenresPageApiData(
    @SerializedName("genres")
    val genres: List<GenreApiData>
) : Serializable
