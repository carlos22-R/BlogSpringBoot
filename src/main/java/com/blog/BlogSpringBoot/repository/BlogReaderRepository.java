package com.blog.BlogSpringBoot.repository;

import com.blog.BlogSpringBoot.entity.BlogsReader;
import com.blog.BlogSpringBoot.entity.BlogsReaderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BlogReaderRepository extends JpaRepository<BlogsReader, BlogsReaderId> {
    @Transactional
    void deleteById_rId(int rId);
}