package com.blog.BlogSpringBoot.controller;

import com.blog.BlogSpringBoot.dto.BlogReaderDTO;
import com.blog.BlogSpringBoot.dto.UserDTO;
import com.blog.BlogSpringBoot.service.BlogService;
import com.blog.BlogSpringBoot.service.ReaderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class AdminController {
    private final BlogService blogService;
    private final ReaderService readerService;
    @Autowired
    public AdminController(BlogService blogService ,ReaderService readerService) {
        this.blogService = blogService;
        this.readerService = readerService;
    }
    @GetMapping("/blog")
    public String homePageAdmin(Model model, HttpSession session, HttpServletRequest request) {
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("Blogs",blogService.GetBlog());
        model.addAttribute("request", request);
        return "/admin/homeAdmin";
    }
    @GetMapping("/deleteBlog/{id}")
    public String deleteBlog(@PathVariable("id") int id) {
        System.out.println("deleteBlog");
        blogService.deleteBlog(id);
        return "redirect:/blog";
    }
    @GetMapping("blog/updateBlog/{id}")
    public String updateBlog(@PathVariable("id") int id,Model model, HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("blog",blogService.BlogFindById(id));
        return "/admin/updateAdmin";
    }
    @PostMapping("blog/BlogUpdate/{id}")
    public String updateBlogs(@PathVariable("id") int id,@ModelAttribute BlogReaderDTO blogDTO) {
        blogService.updateBlog(id,blogDTO);
        return "redirect:/blog";
    }
    @GetMapping("/viewBlog/{id}")
    public String viewBlog(@PathVariable("id") int id,Model model, HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("blog",blogService.BlogFindById(id));
        return "/admin/viewAdmin";
    }
    @GetMapping("blog/readersAdmin")
    public String readersAdmin(Model model, HttpSession session, HttpServletRequest request) {
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("readers",readerService.getAllReaders());
        model.addAttribute("request", request);
        return "/admin/UserAdmin";
    }
    @GetMapping("blog/createBlogAdmin")
    public String createBlog(Model model, HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        return "/admin/createBlog";
    }
    @PostMapping("/blog/saveBlog")
    public String saveBlog(@ModelAttribute BlogReaderDTO blogDTO) {
        blogService.saveBlog(blogDTO);
        return "redirect:/blog";
    }
    @GetMapping("/deleteReader/{id}")
    public String deleteReader(@PathVariable("id") int id) {
        readerService.deleteReaderById(id);
        return "redirect:/blog/readersAdmin";
    }
    @GetMapping("blog/updateReader/{id}")
    public String updateReader(@PathVariable("id") int id,Model model, HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        model.addAttribute("reader",readerService.getReaderById(id));
        model.addAttribute("usuario",readerService.getUserById(id));
        return "/admin/userUpdateAdmin";
    }
    @PostMapping("blog/ReaderUpdate/{id}")
    public String updateReader(@PathVariable("id") int id,@ModelAttribute UserDTO userDTO) {
        System.out.println(userDTO);
        readerService.updateReaderUser(id,userDTO);
        return "redirect:/blog/readersAdmin";
    }
}
