package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.db.ReviewEntity
import com.github.faening.engsofttmdb.domain.contract.BaseService
import com.github.faening.engsofttmdb.domain.model.Review
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/reviews")
class ReviewController @Autowired constructor(
    service: BaseService<ReviewEntity, Review, Review>
) : BaseController<ReviewEntity, Review, Review>(service)