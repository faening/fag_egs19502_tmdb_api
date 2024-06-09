package com.github.faening.engsofttmdb.domain.mapper

import org.springframework.stereotype.Service

/**
 * Esta classe é responsável por mapear os dados entre a camada de domínio e a camada de dados.
 *
 * @param A (API Data) Tipo de dado obtido da API.
 * @param E (Entity) Tipo de dado armazenado no banco de dados.
 * @param D (Domain Object) Tipo de dado utilizado na camada de domínio.
 */
@Service
interface BaseMapper<A, E, D> {
    fun fromApiDataToEntity(data: A): E

    fun fromEntityToDomain(entity: E): D

    fun fromDomainToEntity(domain: D): E

    fun mergeEntityAndRequest(entity: E, request: D): E
}