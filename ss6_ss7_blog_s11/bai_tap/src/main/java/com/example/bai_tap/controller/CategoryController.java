package com.example.bai_tap.controller;

import com.example.bai_tap.model.Category;
import com.example.bai_tap.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public String showCategory(Model model) {
        List<Category> categories = categoryService.getAll();
        if (categories.isEmpty()) {
            model.addAttribute("message", "No categories found.");
        } else {
            model.addAttribute("categories", categories);
        }
        return "category/list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        categoryService.addNew(category);
        redirectAttributes.addFlashAttribute("message", "Category added successfully.");
        return "redirect:/categories";
    }

    @GetMapping("/{id}/update")
    public String showFormUpdate(@PathVariable("id") int id, Model model) {
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "category/update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") int id, @ModelAttribute("category") Category category,
                         RedirectAttributes redirectAttributes) {
        category.setId(id);
        categoryService.update(category);
        redirectAttributes.addFlashAttribute("message", "Update category successfully!");
        return "redirect:/categories";
    }

    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Category category = categoryService.getById(id);
        if (category == null) {
            redirectAttributes.addFlashAttribute("error", "Category not found!");
        } else {
            categoryService.delete(category);
            redirectAttributes.addFlashAttribute("message", "Delete category successfully");
        }
        return "redirect:/categories";
    }

    @GetMapping("/{id}")
    public String showCategoryDetails(@PathVariable("id") int id, Model model) {
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "category/details";
    }


}
