package com.github.faening.engsofttmdb.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class Movie(
    val id: Long?,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val idTmdb: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) : Serializable