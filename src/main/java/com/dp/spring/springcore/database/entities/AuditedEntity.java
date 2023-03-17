package com.dp.spring.springcore.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.Serializable;

/**
 * General super-entity to extend in order to inherit auditing features and {@link BaseEntity} features.
 * <br>
 * Please, in order to use these features:
 * <ul>
 *     <li>enable @{@link EnableJpaAuditing} in a @{@link Configuration} class</li>
 *     <li>if necessary, define a custom concrete {@link AuditorAware} in order to retrieve principal information</li>
 * </ul>
 * @param <ID> the entity ID type
 */
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter(AccessLevel.PRIVATE) @Accessors(chain = true)
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditedEntity<ID extends Serializable>
        extends BaseEntity<ID> {
    @CreatedBy
    @Column(updatable = false)
    protected String createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected long createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;

    @LastModifiedDate
    @Column(nullable = false)
    protected long lastModifiedDate;
}
