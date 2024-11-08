package com.blog.BlogSpringBoot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TITLE", length = 50)
    private String title;

    @Column(name = "DESCRIPTION", length = 4000)
    private String description;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "B_ID"),
            inverseJoinColumns = @JoinColumn(name = "R_ID"))
    private Set<com.blog.BlogSpringBoot.entity.Reader> readers = new LinkedHashSet<>();

}