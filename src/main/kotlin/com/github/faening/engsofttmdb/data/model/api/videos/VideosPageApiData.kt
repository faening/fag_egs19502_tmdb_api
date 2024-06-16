package com.github.faening.engsofttmdb.data.model.api.videos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideosPageApiData(
    @SerializedName("id")
    val id: Int,

    @SerializedName("results")
    val results: List<VideoApiData>,
) : Serializable