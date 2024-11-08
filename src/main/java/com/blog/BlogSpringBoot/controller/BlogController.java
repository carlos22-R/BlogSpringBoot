package com.blog.BlogSpringBoot.controller;

import com.blog.BlogSpringBoot.dto.BlogReaderDTO;
import com.blog.BlogSpringBoot.entity.Blog;
import com.blog.BlogSpringBoot.service.BlogReaderService;
import com.blog.BlogSpringBoot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/blogAdmin")
@RestController
public class BlogController {
    private final BlogService blogService;
    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/Get")
    public ResponseEntity<?> GetAllBlogs() {
        return new ResponseEntity<>(blogService.GetBlog(), HttpStatus.OK);
    }

    @GetMapping("/Get/{blogId}")
    public Optional<Blog> GetBlogById(@PathVariable("blogId") int blogid) {
        return blogService.BlogFindById(blogid);
    }

    @GetMapping("/Delete/{blogId}")
    public ResponseEntity<?> DeleteBlogById(@PathVariable("blogId") int blogid) {
        blogService.deleteBlog(blogid);
        return new ResponseEntity<>(blogid,HttpStatus.OK);
    }
    @PostMapping("/Post")
    public ResponseEntity<?> PostBlog(@RequestBody BlogReaderDTO blog) {
        return new ResponseEntity<>(blogService.saveBlog(blog), HttpStatus.OK);
    }
    @PutMapping("/Update/{blogId}")
    public ResponseEntity<?> UpdateBlog(@RequestBody BlogReaderDTO blog,@PathVariable("blogId") int id) {
        return new ResponseEntity<>(blogService.updateBlog(id, blog), HttpStatus.OK);
    }
}
