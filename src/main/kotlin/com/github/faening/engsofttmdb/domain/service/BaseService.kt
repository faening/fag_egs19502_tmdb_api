package com.github.faening.engsofttmdb.domain.service

import org.springframework.stereotype.Service

@Service
abstract class BaseService<REQ, RES> {

    abstract fun getAll(): List<RES>

    abstract fun getById(id: Long): RES

    abstract fun save(request: REQ): RES

    abstract fun saveAll(request: List<REQ>): List<RES>

    abstract fun update(id: Long, request: REQ): RES

    abstract fun delete(id: Long)

}