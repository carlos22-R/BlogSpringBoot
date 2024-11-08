package com.blog.BlogSpringBoot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class BlogsReaderId implements java.io.Serializable {
    private static final long serialVersionUID = 1808540604751793690L;
    @Column(name = "R_ID", nullable = false)
    private Integer rId;

    @Column(name = "B_ID", nullable = false)
    private Integer bId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BlogsReaderId entity = (BlogsReaderId) o;
        return Objects.equals(this.rId, entity.rId) &&
                Objects.equals(this.bId, entity.bId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rId, bId);
    }

}