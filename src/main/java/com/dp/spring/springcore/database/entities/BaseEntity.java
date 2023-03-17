package com.dp.spring.springcore.database.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * General super-entity to extend in order to inherit ID field and overridable callback methods.
 * <br>
 * <b>JPA specification for <i></i>@Pre</i>/<i>@Post</i> features</b>
 * <i>In general, the lifecycle method of a portable application should not invoke EntityManager or Query operations,
 * access other entity instances, or modify relationships within the same persistence context.
 * A lifecycle callback method may modify the non-relationship state of the entity on which it is invoked.</i>I
 * @param <ID> the entity ID type
 */
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter(AccessLevel.PRIVATE) @Accessors(chain = true)
@EqualsAndHashCode
public abstract class BaseEntity<ID extends Serializable> {
    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected ID id;

    @PrePersist
    private void prePersist() {
        this.prePersistCallback();
    }
    protected void prePersistCallback() {}

    @PostPersist
    private void postPersist() {
        this.postPersistCallback();
    }
    protected void postPersistCallback() {}

    @PreUpdate
    private void preUpdate() {
        this.preUpdateCallback();
    }
    protected void preUpdateCallback() {}

    @PostUpdate
    private void postUpdate() {
        this.postUpdateCallback();
    }
    protected void postUpdateCallback() {}

    @PreRemove
    private void preRemove() {
        this.preRemoveCallback();
    }
    protected void preRemoveCallback() {}

    @PostRemove
    private void postRemove() {
        this.postRemoveCallback();
    }
    protected void postRemoveCallback() {}


    @Override
    public String toString() {
        return "id=" + id;
    }
}
