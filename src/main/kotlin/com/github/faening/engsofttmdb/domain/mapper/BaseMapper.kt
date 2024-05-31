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
abstract class BaseMapper<A, E, D> {

    abstract fun fromApiDataToEntity(data: A): E

    abstract fun fromEntityToDomain(entity: E): D

    abstract fun fromDomainToEntity(domain: D): E

}