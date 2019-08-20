package com.che.blog.controller;


import com.che.blog.dto.BlogQueryDTO;
import com.che.blog.entity.Type;
import com.che.blog.iService.IBlogService;
import com.che.blog.iService.ITypeService;
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
public class TypeShowController {

    @Autowired
    private ITypeService iTypeService;

    @Autowired
    private IBlogService iBlogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id,
                        Model model){
        List<Type> types = iTypeService.listTypeTop(10000);
        if (id == -1) {
            id = types.get(0).getId();
        }
        BlogQueryDTO blogQueryDTO = new BlogQueryDTO();
        blogQueryDTO.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",iBlogService.listBlog(pageable,blogQueryDTO));
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
