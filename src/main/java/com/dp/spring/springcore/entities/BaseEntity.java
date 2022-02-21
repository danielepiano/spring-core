package com.dp.spring.springcore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * "Super-entity" to extend in order to inherit basic fields and methods.
 * Overridable callback methods.
 *
 * [JPA specification related to @Pre/@Post features]
 * In general, the lifecycle method of a portable application should not invoke EntityManager or Query operations,
 * access other entity instances, or modify relationships within the same persistence context.
 * A lifecycle callback method may modify the non-relationship state of the entity on which it is invoked.
 *
 * @param <ID>   Entity ID type.
 */
@MappedSuperclass
@Getter @Setter @Accessors(chain = true)
@NoArgsConstructor @AllArgsConstructor
public abstract class BaseEntity<ID extends Serializable> {
    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected ID id;

    @PrePersist
    private void prePersist() {
        // ...
        prePersistCallback();
    }
    protected void prePersistCallback() {}

    @PostPersist
    private void postPersist() {
        // ...
        postPersistCallback();
    }
    protected void postPersistCallback() {}

    @PreUpdate
    private void preUpdate() {
        // ...
        preUpdateCallback();
    }
    protected void preUpdateCallback() {}

    @PostUpdate
    private void postUpdate() {
        // ...
        postUpdateCallback();
    }
    protected void postUpdateCallback() {}

    @PreRemove
    private void preRemove() {
        // ...
        preRemoveCallback();
    }
    protected void preRemoveCallback() {}

    @PostRemove
    private void postRemove() {
        // ...
        postRemoveCallback();
    }
    protected void postRemoveCallback() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
