package com.dp.spring.springcore.database.entities;

import com.dp.spring.springcore.database.repositories.SoftDeleteJpaRepository;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;

/**
 * General super-entity to extend in order to inherit soft deleting feature and {@link AuditedEntity} features.
 * <br>
 * Soft deleted can be enabled either using:
 * <ol>
 *     <li>
 *         {@link SoftDeleteJpaRepository}
 *         <br> Pro: you can query inactive entities
 *         <br> Con: you always have to specify the <i>is_active</i> state if you mostly want to query active records
 *     </li>
 *     <li>
 *         adding to the related-entity class the following annotations:
 *            <br> - to query only active records: <b><i>@{@link Where}(clause = SOFT_DELETE_CLAUSE)</i></b>
 *            <br> - to mark as inactive INSTEAD of deleting: <b><i>@{@link SQLDelete}(sql = "update {tableName} set is_active = false where id = ?")</i></b>
 *         <br> Pro: a where clause is applied so that you don't ever have to repeat the active state for every query
 *         <br> Con: you can't query inactive entities
 *     </li>
 * </ol>
 *
 * @param <ID> the entity ID type
 */
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public abstract class SoftDeletableAuditedEntity<ID extends Serializable> extends AuditedEntity<ID> {
    public static final String SOFT_DELETE_CLAUSE = "is_active IS true";

    @Builder.Default
    @Column(name = "is_active")
    @ColumnDefault("true")
    protected boolean active = true;


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "createdBy = " + createdBy + ", " +
                "createdDate = " + createdDate + ", " +
                "lastModifiedBy = " + lastModifiedBy + ", " +
                "lastModifiedDate = " + lastModifiedDate + ", " +
                "active = " + active +
                ")";
    }
}
