package com.thoughtworks.mall.demo.adapter.persistence;

import com.thoughtworks.mall.demo.domain.repository.BaseRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoRepositoryBean
@SuppressWarnings("unused")
public class DefaultBaseRepository<D, E, I> implements BaseRepository<D, I> {

    protected Class<E> entityClass;
    private Class<D> domainClass;

    private ModelMapper modelMapper;

    protected SimpleJpaRepository<E, I> jpaRepository;

    @SuppressWarnings("unchecked")
    public DefaultBaseRepository(EntityManager entityManager) {
        this.domainClass = (Class<D>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
        this.jpaRepository = new SimpleJpaRepository<>(entityClass, entityManager);

        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    protected E convertDomainToEntity(D domain) {
        return modelMapper.map(domain, entityClass);
    }

    protected D convertEntityToDomain(E entity) {
        return modelMapper.map(entity, domainClass);
    }

    @Override
    public List<D> findAll() {
        return jpaRepository.findAll().stream().map(this::convertEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public List<D> findAll(Sort sort) {
        return jpaRepository.findAll(sort).stream().map(this::convertEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public Page<D> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).map(this::convertEntityToDomain);
    }


    @Override
    public List<D> findAllById(Iterable<I> ids) {
        return jpaRepository.findAllById(ids).stream().map(this::convertEntityToDomain).collect(
                Collectors.toList());
    }

    @Override
    public void deleteById(I id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void delete(D entity) {
        jpaRepository.delete(convertDomainToEntity(entity));
    }

    @Override
    public void deleteAll(Iterable<? extends D> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }

    @Override
    public D save(D entity) {
        return convertEntityToDomain(jpaRepository.save(convertDomainToEntity(entity)));
    }

    @Override
    public List<D> saveAll(Iterable<D> entities) {
        List<D> result = new ArrayList<D>();
        for (D entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    @Override
    public Optional<D> findById(I id) {
        return jpaRepository.findById(id).map(this::convertEntityToDomain);
    }

    @Override
    public boolean existsById(I id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void flush() {
        jpaRepository.flush();
    }

    @Override
    public D saveAndFlush(D entity) {
        return convertEntityToDomain(jpaRepository.save(convertDomainToEntity(entity)));
    }

    @Override
    public void deleteInBatch(Iterable<D> domains) {
        List<E> entities = StreamSupport.stream(domains.spliterator(), false).map(this::convertDomainToEntity).collect(
                Collectors.toList());
        jpaRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        jpaRepository.deleteAllInBatch();
    }

    @Override
    public D getOne(I id) {
        return convertEntityToDomain(jpaRepository.getOne(id));
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }
}
