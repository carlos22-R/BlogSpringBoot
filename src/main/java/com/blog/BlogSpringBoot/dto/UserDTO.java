package com.blog.BlogSpringBoot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
}
