package com.blog.BlogSpringBoot.repository;

import com.blog.BlogSpringBoot.entity.Reader;
import com.blog.BlogSpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
