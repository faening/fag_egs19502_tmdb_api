package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.domain.contract.BaseService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Suppress("unused")
abstract class BaseController<ENT, REQ, RES>(private val service: BaseService<ENT, REQ, RES>) {

    @GetMapping("", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): ResponseEntity<List<RES>> {
        val response = service.getAll()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@PathVariable id: Long): ResponseEntity<RES> {
        val response = service.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(response)
    }

    @PostMapping("", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody request: REQ): ResponseEntity<RES> {
        val response = service.save(request) ?: return ResponseEntity.badRequest().build()
        return ResponseEntity.ok(response)
    }

    @PatchMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@PathVariable id: Long, @RequestBody request: REQ): ResponseEntity<RES> {
        val response = service.update(id, request) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        val response = service.delete(id)
        if (!response) { return ResponseEntity.notFound().build() }
        return ResponseEntity.noContent().build()
    }

}