package com.blog.BlogSpringBoot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "blogs_readers", indexes = {
        @Index(name = "B_ID", columnList = "B_ID")
})
public class BlogsReader {
    @EmbeddedId
    private BlogsReaderId id;

    @MapsId("rId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "R_ID", nullable = false)
    private com.blog.BlogSpringBoot.entity.Reader r;

    @MapsId("bId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "B_ID", nullable = false)
    private Blog b;

}