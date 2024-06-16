package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.db.AuthorDetailsEntity
import com.github.faening.engsofttmdb.domain.model.AuthorDetails
import com.github.faening.engsofttmdb.domain.service.AuthorDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/author-details")
class AuthorDetailsController @Autowired constructor(
    service: AuthorDetailsService
) : BaseController<AuthorDetailsEntity, AuthorDetails, AuthorDetails>(service)