package com.blog.BlogSpringBoot.service;

import com.blog.BlogSpringBoot.dto.BlogReaderDTO;
import com.blog.BlogSpringBoot.dto.ReaderDTO;
import com.blog.BlogSpringBoot.entity.Blog;
import com.blog.BlogSpringBoot.entity.Reader;
import com.blog.BlogSpringBoot.repository.BlogRepository;
import com.blog.BlogSpringBoot.repository.ReadersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;

@Service
public class BlogReaderService {
    public final ReadersRepository readerRepository;
    public final BlogRepository blogRepository;
    @Autowired
    public BlogReaderService(ReadersRepository readerRepository, BlogRepository blogRepository) {
        this.readerRepository = readerRepository;
        this.blogRepository = blogRepository;
    }

    public void saveBlogReader(BlogReaderDTO blogReaderDTO){
        UUID uuid = UUID.randomUUID();
        Blog blog = new Blog();
        blog.setDescription(blogReaderDTO.getDescription());
        blog.setTitle(blogReaderDTO.getTitle());
        blog.setId((int)uuid.getMostSignificantBits()&Integer.MAX_VALUE);

        for (ReaderDTO reader: blogReaderDTO.getReaders()){
            Reader reader1 = new Reader();
            reader1.setEmail(reader.getEmail());
            reader1.setName(reader.getName());
            UUID uuid2 = UUID.randomUUID();
            reader1.setId((int)uuid2.getMostSignificantBits()&Integer.MAX_VALUE );
            Reader newReader = readerRepository.save(reader1);
            blog.getReaders().add(newReader);
        }
        blogRepository.save(blog);
    }
    public List<Blog> GetBlog(){

        return blogRepository.findAll();
    }
    public void setBlogReader(int readerId,int blogId){
        Reader reader = readerRepository.findById(readerId).orElseThrow(()->new InvalidParameterException("Invalid id"));
        Blog blog = blogRepository.findById(blogId).orElseThrow(()->new InvalidParameterException("Invalid id"));
        blog.getReaders().add(reader);
        blogRepository.save(blog);
    }
    public void popBlogReader(int readerId,int blogId){
        Blog blog = blogRepository.findById(blogId).orElseThrow(()->new InvalidParameterException("Invalid id"));
        blog.getReaders().removeIf(reader->reader.getId().equals(readerId));
        blogRepository.save(blog);
    }
}
