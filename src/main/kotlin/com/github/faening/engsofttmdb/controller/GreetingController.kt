package com.github.faening.engsofttmdb.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/greeting")
class GreetingController {

    @GetMapping
    fun greeting(): String {
        return "Hello, World!"
    }

}