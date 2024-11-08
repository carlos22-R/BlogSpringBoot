package com.blog.BlogSpringBoot.repository;

import com.blog.BlogSpringBoot.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadersRepository extends JpaRepository<Reader, Integer> {
    @Query("SELECT u FROM Reader u WHERE u.name = :name")
    Optional<Reader> findByName(String name);
}
