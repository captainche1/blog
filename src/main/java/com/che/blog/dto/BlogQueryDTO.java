package com.che.blog.dto;

import lombok.Data;

@Data
public class BlogQueryDTO {

    private String title;
    private Long typeId;
    private boolean recommend;

    public BlogQueryDTO() {
    }
}
