package com.github.faening.engsofttmdb.data.model.api.genres

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenreApiData(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
) : Serializable
