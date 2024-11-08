package com.blog.BlogSpringBoot.dto;

import com.blog.BlogSpringBoot.entity.Reader;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogReaderDTO {

    private String title;

    private String description;

    private Set<ReaderDTO> readers = new LinkedHashSet<>();
}
