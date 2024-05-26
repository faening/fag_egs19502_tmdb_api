package com.github.faening.engsofttmdb.domain.mapper

/**
 * Esta classe é responsável por mapear os dados entre a camada de domínio e a camada de dados.
 *
 * @param D (Data) Tipo de dado obtido da API.
 * @param E (Entity) Tipo de dado armazenado no banco de dados.
 * @param V (Value Object) Tipo de dado utilizado na camada de domínio.
 */
abstract class BaseMapper<D, E, V> {

    abstract fun fromApiDataToEntity(data: D): E

    abstract fun fromEntityToDomain(entity: E): V

    abstract fun fromDomainToEntity(domain: V): E

}