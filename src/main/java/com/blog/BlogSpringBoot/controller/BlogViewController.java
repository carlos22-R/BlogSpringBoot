package com.blog.BlogSpringBoot.controller;

import com.blog.BlogSpringBoot.service.ReaderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.blog.BlogSpringBoot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogViewController {
    private final BlogService blogService;
    private final ReaderService readerService;
    @Autowired
    public BlogViewController(BlogService blogService ,ReaderService readerService) {
        this.blogService = blogService;
        this.readerService = readerService;
    }

    @GetMapping("/home")
    public String homePage(Model model ,HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("Blogs",blogService.GetBlog());
        return "/blogs/home";
    }

    @GetMapping("/readers")
    public String readersPage(Model model,HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("readers",readerService.getAllReaders());
        return "/blogs/readers";
    }
    @GetMapping("/readersBlog")
    public String readersBlogPage(Model model, HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("Blogs",blogService.GetBlog());
        return "/blogs/blogReaders";
    }
}
