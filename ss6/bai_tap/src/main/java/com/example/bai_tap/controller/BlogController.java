package com.example.bai_tap.controller;

import com.example.bai_tap.model.Blog;
import com.example.bai_tap.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("")
    public String showAllBlogs(Model model) {
        List<Blog> blogs = blogService.getAll();
        if (blogs.isEmpty()) {
            model.addAttribute("message", "No blog posts found.");
        } else {
            model.addAttribute("blogs", blogs);
        }
        return "blog/list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("blog") Blog blog, RedirectAttributes redirectAttributes) {
        blogService.addNew(blog);
        redirectAttributes.addFlashAttribute("message", "Create blog successfully!");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/update")
    public String showFormUpdate(@PathVariable("id") int id, Model model) {
        Blog blog = blogService.getById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") int id, @ModelAttribute("blog") Blog blog,
                         RedirectAttributes redirectAttributes) {
        blog.setId(id);
        blogService.update(blog);
        redirectAttributes.addFlashAttribute("message", "Update blog successfully!");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Blog blog = blogService.getById(id);
        if (blog != null) {
            blogService.delete(blog);  // Xóa blog
            redirectAttributes.addFlashAttribute("message", "Delete blog successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Blog not found!");
        }
        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String showBlogDetails(@PathVariable("id") int id, Model model) {
        Blog blog = blogService.getById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/details";
    }
}
