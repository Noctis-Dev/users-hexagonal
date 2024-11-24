package com.qride.users.infraestructure.mapper;

public interface IBaseMapper<D, E> {
    D toDomain(E entity);
    E toEntity(D model);
}
