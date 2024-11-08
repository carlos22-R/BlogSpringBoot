package com.blog.BlogSpringBoot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {
    public String name;
    public String password;
}
