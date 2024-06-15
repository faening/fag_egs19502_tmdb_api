package com.github.faening.engsofttmdb.data.model.api.reviews

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthorDetailsApiData(
    @SerializedName("name")
    val name: String?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("avatar_path")
    val avatarPath: String?,

    @SerializedName("rating")
    val rating: Int?
) : Serializable
