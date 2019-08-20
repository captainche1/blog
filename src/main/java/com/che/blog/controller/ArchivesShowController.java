package com.che.blog.controller;


import com.che.blog.iService.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchivesShowController {

    @Autowired
    private IBlogService iBlogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", iBlogService.archivesBlog());
        model.addAttribute("blogCount", iBlogService.countBlog());
        return "archives";
    }
}
