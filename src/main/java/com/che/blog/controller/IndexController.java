package com.che.blog.controller;

import com.che.blog.iService.IBlogService;
import com.che.blog.iService.ITagService;
import com.che.blog.iService.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private IBlogService iBlogService;

    @Autowired
    private ITypeService iTypeService;

    @Autowired
    private ITagService iTagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", iBlogService.listBlog(pageable));
        model.addAttribute("types", iTypeService.listTypeTop(6));
        model.addAttribute("tags", iTagService.listTagTop(10));
        model.addAttribute("recommendBlogs", iBlogService.listRecommendBlogTop(8));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", iBlogService.getAndConvert(id));
        return "blog";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", iBlogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", iBlogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }
}

