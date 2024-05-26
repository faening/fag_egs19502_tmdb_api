package com.github.faening.engsofttmdb.domain.service

abstract class AbstractService<REQ, RES> {

    abstract fun getAll(): List<RES>

    abstract fun getById(id: Int): RES

    abstract fun create(request: REQ): RES

    abstract fun update(id: Int, request: REQ): RES

    abstract fun delete(id: Int)

}