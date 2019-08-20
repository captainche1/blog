package com.che.blog.controller;


import com.che.blog.entity.Tag;
import com.che.blog.iService.IBlogService;
import com.che.blog.iService.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private ITagService iTagService;

    @Autowired
    private IBlogService iBlogService;

    @GetMapping("/tags/{id}")
    public String Tags(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id,
                        Model model){
        List<Tag> Tags = iTagService.listTagTop(10000);
        if (id == -1) {
            id = Tags.get(0).getId();
        }
        model.addAttribute("tags",Tags);
        model.addAttribute("page",iBlogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
