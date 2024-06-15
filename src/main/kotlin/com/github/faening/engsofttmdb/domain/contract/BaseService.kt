package com.github.faening.engsofttmdb.domain.contract

import org.springframework.stereotype.Service

@Service
interface BaseService<ENT, REQ, RES> {

    fun getAllEntities(): List<ENT>

    fun getAll(): List<RES>

    fun getEntityById(id: Long): ENT

    fun getById(id: Long): RES

    fun saveEntity(entity: ENT): ENT

    fun saveAllEntities(entities: List<ENT>): List<ENT>

    fun save(request: REQ): RES

    fun saveAll(request: List<REQ>): List<RES>

    fun updateEntity(entity: ENT): RES

    fun update(id: Long, request: REQ): RES

    fun deleteEntity(entity: ENT) : Boolean

    fun delete(id: Long) : Boolean

}