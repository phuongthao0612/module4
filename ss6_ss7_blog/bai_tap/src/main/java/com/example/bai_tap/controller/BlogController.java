package com.example.bai_tap.controller;

import com.example.bai_tap.exception.ResourceNotFoundException;
import com.example.bai_tap.model.Blog;
import com.example.bai_tap.service.IBlogService;
import com.example.bai_tap.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public String showAllBlogs(Model model, @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "createdAt") String sortBy,
                               @RequestParam(required = false) String title) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(sortBy).ascending());
        Page<Blog> blogs;
        if (title != null && !title.isEmpty()) {
            blogs = blogService.searchByTitle(title, pageable);
        } else {
            blogs = blogService.getAll(pageable);
        }
        model.addAttribute("blogs", blogs);
        model.addAttribute("title", title);
        return "blog/list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.getAll());
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
            throw new ResourceNotFoundException("Blog with ID " + id + " not found.");
        }
        model.addAttribute("blog", blog);
        model.addAttribute("categories", categoryService.getAll());
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
            blogService.delete(blog);
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
            throw new ResourceNotFoundException("Blog with ID " + id + " not found.");
        }
        model.addAttribute("blog", blog);
        return "blog/details";
    }
}
