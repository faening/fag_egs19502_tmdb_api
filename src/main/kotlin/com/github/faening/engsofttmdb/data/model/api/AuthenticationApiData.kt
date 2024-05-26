package com.github.faening.engsofttmdb.data.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthenticationApiData(
    @SerializedName("success")
    var success: Boolean,

    @SerializedName("status_code")
    var statusCode: Int,

    @SerializedName("status_message")
    var statusMessage: String,
) : Serializable