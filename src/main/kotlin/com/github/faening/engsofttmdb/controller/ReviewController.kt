package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.db.ReviewEntity
import com.github.faening.engsofttmdb.domain.model.Review
import com.github.faening.engsofttmdb.domain.service.ReviewService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/reviews")
class ReviewController @Autowired constructor(
    service: ReviewService
) : BaseController<ReviewEntity, Review, Review>(service)