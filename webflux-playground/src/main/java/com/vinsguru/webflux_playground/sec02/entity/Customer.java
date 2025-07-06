package com.vinsguru.webflux_playground.sec02.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("customer")
@Getter
@Setter
@ToString
public class Customer {

    @Id
    private Integer id;

    @Column("name")
    private String name;
    private String email;

}
