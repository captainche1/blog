package com.che.blog.iService;

import com.che.blog.dto.BlogQueryDTO;
import com.che.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQueryDTO blog);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
