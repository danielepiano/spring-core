package com.dp.spring.springcore.database.repositories;

import com.dp.spring.springcore.database.entities.BaseEntity;
import com.dp.spring.springcore.database.entities.SoftDeletableAuditedEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

/**
 * General JPA repository to extend in order to implement soft-delete methods.
 *
 * @param <T>  the entity type, that should extend {@link BaseEntity}
 * @param <ID> the entity ID type
 */
@NoRepositoryBean
public interface SoftDeleteJpaRepository<T extends SoftDeletableAuditedEntity<ID>, ID extends Serializable>
        extends BaseJpaRepository<T, ID> {
    // --- DEFAULT QUERIES OVERRIDDEN FOR RETRIEVING ACTIVE RESOURCES ---
    /*@Override
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
    long count();*/


    // ---  QUERIES FOR SOFT-DELETING RESOURCES ---
    default void softDeleteById(ID id) {
        Optional<T> entity = this.findById(id);
        if (entity.isPresent()) {
            T toSoftDelete = entity.get();
            toSoftDelete.setActive(false);
            this.save(toSoftDelete);
        }
    }

    default void softDelete(T entity) {
        if (this.findById(entity.getId()).isPresent()) {
            entity.setActive(false);
            this.save(entity);
        }
    }

    default void softDeleteAllById(Iterable<? extends ID> ids) {
        ids.forEach(this::softDeleteById);
    }

    default void softDeleteAll(Iterable<? extends T> entities) {
        entities.forEach(this::softDelete);
    }

    default void softDeleteAll() {
        this.findAll().forEach(this::softDelete);
    }

    @Query("update #{#entityName} e set e.active = false where e.id in ?1")
    @Modifying
    void softDeleteAllByIdInBatch(Iterable<ID> ids);

    @Query("update #{#entityName} e set e.active = false")
    @Modifying
    void softDeleteAllInBatch();


    // --- QUERIES FOR RETRIEVING INACTIVE RESOURCES --- don't work if entity is annotated with @Where
    /*@Transactional(readOnly = true)
    @Query("select e from #{#entityName} e")
    List<T> findAllByActiveFalse();

    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.active = false")
    long countByActiveFalse();*/
}
