package com.che.blog.iService;

import com.che.blog.dto.BlogQueryDTO;
import com.che.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IBlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQueryDTO blog);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(String query, Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Long tagId, Pageable pageable);

    Map<String,List<Blog>> archivesBlog();

    Long countBlog();
}
