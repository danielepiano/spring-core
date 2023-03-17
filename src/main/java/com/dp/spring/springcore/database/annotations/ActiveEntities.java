package com.dp.spring.springcore.database.annotations;

import com.dp.spring.springcore.database.entities.SoftDeletableAuditedEntity;
import org.hibernate.annotations.Where;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Where(clause = SoftDeletableAuditedEntity.SOFT_DELETE_CLAUSE)
public @interface ActiveEntities {
}
