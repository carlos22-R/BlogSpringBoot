package com.blog.BlogSpringBoot.controller;

import com.blog.BlogSpringBoot.dto.LoginDTO;
import com.blog.BlogSpringBoot.dto.ReaderDTO;
import com.blog.BlogSpringBoot.dto.UserDTO;
import com.blog.BlogSpringBoot.entity.User;
import com.blog.BlogSpringBoot.service.ReaderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")

public class SessionController {
    private final ReaderService readerService;
    @Autowired
    public SessionController(ReaderService readerService) {
        this.readerService = readerService;
    }
    @GetMapping("/")
    public String login(Model model) {
//        model.addAttribute("user",);
        return "blogs/Login";
    }
    @PostMapping("/session")
    public String session(@RequestParam String name,Model model ,@RequestParam String password) {
        UserDTO usuario = readerService.findUserByReader(name,password);
        if ( usuario != null) {

            model.addAttribute("user",usuario);
            return "redirect:/home";

        }
        model.addAttribute("error","Invalid user or password");
        return "blogs/Login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/record")
    public String record() {
        return "blogs/register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO userDTO) {
        readerService.saveUser(userDTO);
        return "redirect:/";
    }
}
