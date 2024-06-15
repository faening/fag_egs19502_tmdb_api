package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.db.AuthorDetailsEntity
import com.github.faening.engsofttmdb.domain.contract.BaseService
import com.github.faening.engsofttmdb.domain.model.AuthorDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/author-details")
class AuthorDetailsController @Autowired constructor(
    service: BaseService<AuthorDetailsEntity, AuthorDetails, AuthorDetails>
) : BaseController<AuthorDetailsEntity, AuthorDetails, AuthorDetails>(service)