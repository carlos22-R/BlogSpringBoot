package com.blog.BlogSpringBoot.service;

import com.blog.BlogSpringBoot.dto.BlogReaderDTO;
import com.blog.BlogSpringBoot.entity.Blog;
import com.blog.BlogSpringBoot.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    public List<Blog> GetBlog(){

        return blogRepository.findAll();
    }
    public Blog saveBlog(BlogReaderDTO blog){
        Blog blogEntity = new Blog();
        UUID uuid = UUID.randomUUID();
        blogEntity.setTitle(blog.getTitle());
        blogEntity.setDescription(blog.getDescription());
        blogEntity.setId((int)uuid.getMostSignificantBits()&Integer.MAX_VALUE);
        return blogRepository.save(blogEntity);
    }

    public Optional<Blog> BlogFindById(int id){
        return blogRepository.findById(id);
    }

    public Blog updateBlog(int id, BlogReaderDTO blog){
        Blog blogEntity = blogRepository.findById(id).orElseThrow(()->new InvalidParameterException("Invalid id"));
        blogEntity.setTitle(blog.getTitle());
        blogEntity.setDescription(blog.getDescription());
        return blogRepository.save(blogEntity);
    }
    public void deleteBlog(int id){
        blogRepository.deleteById(id);
    }
}
