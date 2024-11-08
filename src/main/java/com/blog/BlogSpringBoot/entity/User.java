package com.blog.BlogSpringBoot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name = "users")
public class User {
    @Id
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;

}
