package com.dp.spring.springcore.database.repositories;

import com.dp.spring.springcore.database.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * General JPA repository to extend for {@link BaseEntity} database operations.
 * @param <T> the entity type, that should extend {@link BaseEntity}
 * @param <ID> the entity ID type
 */
@NoRepositoryBean
public interface BaseJpaRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
