package com.dp.spring.springcore.repositories;

import com.dp.spring.springcore.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Base repository.
 * @param <T>   Entity type.
 * @param <ID>  Entity ID type.
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
