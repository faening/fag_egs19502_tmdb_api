package com.github.faening.engsofttmdb.data.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CreditsData(
    @SerializedName("id")
    val id : Int,

    @SerializedName("cast")
    val cast : List<CastApiData>,

    @SerializedName("crew")
    val crew : List<CrewApiData>
) : Serializable

