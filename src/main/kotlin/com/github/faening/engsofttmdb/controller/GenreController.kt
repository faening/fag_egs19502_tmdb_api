package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.domain.model.Genre
import com.github.faening.engsofttmdb.domain.service.GenreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/genres")
class GenreController @Autowired constructor(
    service: GenreService
) : BaseController<GenreEntity, Genre, Genre>(service)