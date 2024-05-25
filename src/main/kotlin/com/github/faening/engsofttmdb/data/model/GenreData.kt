package com.github.faening.engsofttmdb.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenreData(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
) : Serializable
