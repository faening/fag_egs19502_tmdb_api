package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.domain.model.Movie
import com.github.faening.engsofttmdb.domain.service.AbstractService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/movies")
class MovieController @Autowired constructor(
    service: AbstractService<Movie, Movie>
) : AbstractController<Movie, Movie>(service) {


}