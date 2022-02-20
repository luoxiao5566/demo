package com.thoughtworks.mall.demo.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<D, I> {

    List<D> findAll();

    List<D> findAll(Sort sort);

    Page<D> findAll(Pageable pageable);

    List<D> findAllById(Iterable<I> ids);

    void deleteById(I id);

    void delete(D entity);

    void deleteAll(Iterable<? extends D> entities);

    void deleteAll();

    D save(D entity);

    List<D> saveAll(Iterable<D> entities);

    Optional<D> findById(I id);

    boolean existsById(I id);

    void flush();

    D saveAndFlush(D entity);

    void deleteInBatch(Iterable<D> domains);

    void deleteAllInBatch();

    D getOne(I id);

    long count();

}
