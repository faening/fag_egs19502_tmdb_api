package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.db.VideoEntity
import com.github.faening.engsofttmdb.domain.model.Video
import com.github.faening.engsofttmdb.domain.service.VideoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/videos")
class VideoController @Autowired constructor(
    service: VideoService<VideoEntity, Video, Video>
) : BaseController<VideoEntity, Video, Video>(service)