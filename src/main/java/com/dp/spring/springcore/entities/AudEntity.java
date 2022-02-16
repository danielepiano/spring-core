package com.dp.spring.springcore.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// @TODO: 28/01/2022  testare funzionamento createdBY e lastModifiedBY con SpringSecurity

/**
 * Entity to extend in order to inherit auditing fields as well as basic fields and methods.
 *
 * ! @EnableJpaAuditing in a configuration class (i.e. SpringBootApplication one) to use Spring auditing features.
 *
 * @param <ID>   Entity ID type.
 */
@MappedSuperclass
@EntityListeners( AuditingEntityListener.class )
@Getter @Setter
public abstract class AudEntity<ID extends Serializable> extends BaseEntity<ID> {
    @CreatedBy
    @Column( name = "created_by" )
    private String createdBy;

    @CreatedDate @Temporal(TemporalType.TIMESTAMP)
    @Column( name = "created_date" )
    @Setter( AccessLevel.PRIVATE )
    private Date createdDate;

    @LastModifiedBy
    @Column( name = "last_modified_by" )
    @Setter( AccessLevel.PRIVATE )
    private String lastModifiedBy;

    @LastModifiedDate @Temporal(TemporalType.TIMESTAMP)
    @Column( name = "last_modified_date" )
    @Setter( AccessLevel.PRIVATE )
    private Date lastModifiedDate;
}
