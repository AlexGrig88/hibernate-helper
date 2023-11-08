package com.grig.edu.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee", schema = "public")
// @TypeDef(name = "myjsonbname", typeClass = JsonBinaryType.class)
public class Employee {

    @Id
    private String username;

    @Embedded
    @AttributeOverride(name = "bDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonBinaryType")
    // @Type(type = "jsonb")
    private String info;

}
