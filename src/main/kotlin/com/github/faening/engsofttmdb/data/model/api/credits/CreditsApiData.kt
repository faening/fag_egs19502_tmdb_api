package com.github.faening.engsofttmdb.data.model.api.credits

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CreditsApiData(
    @SerializedName("id")
    val movieId : Long,

    @SerializedName("cast")
    val cast : List<CastApiData>,

    @SerializedName("crew")
    val crew : List<CrewApiData>
) : Serializable