package com.github.faening.engsofttmdb.domain.model

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class Movie(
    val id: Long? = null,
    val adult: Boolean? = null,
    val backdropPath: String,
    val genreIds: List<Int>,
    val idTmdb: Int? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String,
    val releaseDate: LocalDate,
    val title: String,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) : Serializable