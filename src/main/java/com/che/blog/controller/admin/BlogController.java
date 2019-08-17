package com.che.blog.controller.admin;


import com.che.blog.dto.BlogQueryDTO;
import com.che.blog.entity.Blog;
import com.che.blog.iService.IBlogService;
import com.che.blog.iService.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private IBlogService iBlogService;

    @Autowired
    private ITypeService iTypeService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQueryDTO blog, Model model) {
        model.addAttribute("types",iTypeService.listType());
        model.addAttribute("page", iBlogService.listBlog(pageable, blog));
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQueryDTO blog, Model model) {
        model.addAttribute("page", iBlogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }


}
