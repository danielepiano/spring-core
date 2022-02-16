package com.dp.spring.springcore.repositories;

import com.dp.spring.springcore.entities.AudEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Repository to extend in order to implement soft delete mechanism through overridden retrieve/update/delete methods.
 *
 * ! You always have to specify the active state for every query:
 *      - for active-entities custom queries, type "ActiveTrue" at the end of the method name
 *      - you can query inactive (deleted) entities by typing "ActiveFalse" at the end of the method name
 * @param <T>   Entity type.
 * @param <ID>  Entity ID type.
 */
@NoRepositoryBean
public interface SoftDeleteJpaRepository<T extends AudEntity<ID>, ID extends Serializable>
        extends BaseRepository<T, ID> {
    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.active = true")
    List<T> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.active = true")
    List<T> findAll(Sort sort);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.active = true")
    Page<T> findAll(Pageable pageable);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.active = true")
    Optional<T> findById(ID id);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.active = true")
    T getById(ID id);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id in ?1 and e.active = true")
    List<T> findAllById(Iterable<ID> ids);

    @Override
    @Transactional(readOnly = true)
    default boolean existsById(ID id) { return findById(id) != null;}

    @Override
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.active = true")
    long count();

    @Override
    @Query("update #{#entityName} e set e.active = false where e.id = ?1")
    @Modifying
    void deleteById(ID id);

    @Override
    default void delete(T entity) { deleteById(entity.getId());}

    @Override
    default void deleteAllById(Iterable<? extends ID> ids) { ids.forEach(this::deleteById);}

    @Override
    default void deleteAll(Iterable<? extends T> entities) { entities.forEach(this::delete);}

    @Override
    default void deleteAll() { findAll().forEach(this::delete);}

    /* void deleteAllInBatch(Iterable<T> entities);

    void deleteAllByIdInBatch(Iterable<ID> ids);

    void deleteAllInBatch();*/

    /**
     * Query for deleted entities.
     */
    @Transactional(readOnly = true)
    List<T> findByActiveFalse();

    @Transactional(readOnly = true)
    List<T> findByActiveFalse(Sort sort);

    @Transactional(readOnly = true)
    List<T> findByActiveFalse(Pageable pageable);

    @Transactional(readOnly = true)
    long countByActiveFalse();
}
