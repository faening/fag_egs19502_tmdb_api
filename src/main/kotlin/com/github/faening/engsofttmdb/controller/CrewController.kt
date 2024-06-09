package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.db.CrewEntity
import com.github.faening.engsofttmdb.domain.model.Crew
import com.github.faening.engsofttmdb.domain.service.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/crews")
class CrewController @Autowired constructor(
    service: BaseService<CrewEntity, Crew, Crew>
) : BaseController<CrewEntity, Crew, Crew>(service)