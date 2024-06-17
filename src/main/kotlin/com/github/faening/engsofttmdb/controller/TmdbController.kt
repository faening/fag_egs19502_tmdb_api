package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.api.authentication.AuthenticationApiData
import com.github.faening.engsofttmdb.domain.service.TmdbService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/tmdb")
class TmdbController @Autowired constructor(
    private val tmdbService: TmdbService
) {

    @GetMapping("/authenticate")
    fun authentication() : ResponseEntity<AuthenticationApiData> {
        return ResponseEntity.ok(tmdbService.authentication())
    }

    @GetMapping("/populate")
    fun populate() {
        tmdbService.fetchDataAndPopulateLocalDatabase()
    }

}