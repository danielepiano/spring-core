package com.dp.spring.springcore.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Entity to extend in order to implement soft deletes (as well as auditing features).
 *
 * Soft deleted can be enabled either using
 *      i)  a SoftDeleteJpaRepository: you can query inactive entities, but you always have to specify the is_active state
 *      ii) or adding to the related-entity class the
 *          - @Where(clause = "is_active = true")       (not written in this class because the annotation is not
 *                                                       repeatable and may be used in addition to other clauses)
 *          - @SQLDelete(sql = "update {tableName} set is_active = false where id = ?")
 *          annotations: you can't query inactive entities, but a where-filter is always applied so that you don't have
 *          to repeat the active state for every query.
 *
 * @param <ID>   Entity ID type.
 */
@MappedSuperclass
@Getter @Setter
public abstract class SoftDelAudEntity<ID extends Serializable> extends AudEntity<ID>{
    @Column( name = "is_active" )
    private Boolean active = Boolean.TRUE;

    @Override
    protected void preRemoveCallback() {
        active = Boolean.FALSE;
    }
}
