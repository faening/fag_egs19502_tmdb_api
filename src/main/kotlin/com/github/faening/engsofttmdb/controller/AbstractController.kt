package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.domain.service.AbstractService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

abstract class AbstractController<REQ, RES>(private val service: AbstractService<REQ, RES>) {

    @GetMapping("", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): ResponseEntity<List<RES>> {
        val response = service.getAll()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@PathVariable id: Int): ResponseEntity<RES> {
        val response = service.getById(id)
        return ResponseEntity.ok(response)
    }

    @PostMapping("", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody request: REQ): ResponseEntity<RES> {
        val created = service.create(request)
        return ResponseEntity.ok(created)
    }

    @PatchMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@PathVariable id: Int, @RequestBody request: REQ): ResponseEntity<RES> {
        val updated = service.update(id, request)
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}