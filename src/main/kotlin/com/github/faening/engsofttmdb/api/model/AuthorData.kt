package com.github.faening.engsofttmdb.api.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthorData(
    @SerializedName("name")
    val name: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("avatar_path")
    val avatarPath: String?,

    @SerializedName("rating")
    val rating: Int
) : Serializable
