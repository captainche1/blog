package com.che.blog.controller.admin;


import com.che.blog.dto.BlogQueryDTO;
import com.che.blog.entity.Blog;
import com.che.blog.entity.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private IBlogService iBlogService;

    @Autowired
    private ITypeService iTypeService;

    @Autowired
    private ITagService iTagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQueryDTO blog, Model model) {
        model.addAttribute("types", iTypeService.listType());
        model.addAttribute("page", iBlogService.listBlog(pageable, blog));
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQueryDTO blog, Model model) {
        model.addAttribute("page", iBlogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", iTypeService.listType());
        model.addAttribute("tags", iTagService.listTag());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("types", iTypeService.listType());
        model.addAttribute("tags", iTagService.listTag());
        Blog blog = iBlogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        return "admin/blogs-input";
    }


    @PostMapping("/blogs")
    public String post(Blog blog,
                       HttpSession session,
                       RedirectAttributes attributes) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(iTypeService.getType(blog.getType().getId()));
        blog.setTags(iTagService.listTag(blog.getTagIds()));
        Blog b = iBlogService.saveBlog(blog);
        if (b == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        iBlogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

}
