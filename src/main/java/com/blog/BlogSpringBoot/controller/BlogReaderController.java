package com.blog.BlogSpringBoot.controller;

import com.blog.BlogSpringBoot.dto.BlogReaderDTO;
import com.blog.BlogSpringBoot.service.BlogReaderService;
import com.blog.BlogSpringBoot.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/blog")
@RestController
@Slf4j
public class BlogReaderController {
    private final BlogReaderService blogReaderService;
    @Autowired
    public BlogReaderController(BlogReaderService blogReaderService) {
        this.blogReaderService = blogReaderService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(blogReaderService.GetBlog(), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> post(@RequestBody BlogReaderDTO blogReaderDTO) {
        log.info(blogReaderDTO.toString());
        blogReaderService.saveBlogReader(blogReaderDTO);
        return new ResponseEntity<>("Guardado con exito",HttpStatus.OK);
    }
    @PutMapping("/addReader/{readerId}/{blogId}")
    public ResponseEntity<?> addBlogReader(@PathVariable int readerId, @PathVariable int blogId) {
        try {

            blogReaderService.setBlogReader(readerId,blogId);
            return new ResponseEntity<>("Guardado con exito",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Fallo al agregar referencia", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/removeReader/{readerId}/{blogId}")
    public ResponseEntity<?> removeBlogReader(@PathVariable int readerId, @PathVariable int blogId) {
        try {

            blogReaderService.popBlogReader(readerId,blogId);
            return new ResponseEntity<>("eliminado con exito",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Fallo al borar la referencia",HttpStatus.BAD_REQUEST);
        }
    }
}
