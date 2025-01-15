package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String showForm(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product
            , RedirectAttributes redirectAttributes) {
        productService.add(product);
        redirectAttributes.addFlashAttribute("message", "Product created");
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String editFormUpdate(@PathVariable("id") int id, Model model) {
        Product product = productService.getById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "update";
        }
        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        product.setId(id);
        productService.update(product);
        redirectAttributes.addFlashAttribute("message", "Product updated");
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        productService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Product deleted");
        return "redirect:/products";
    }

    @PostMapping("/search")
    public String search(@RequestParam String name, Model model) {
        model.addAttribute("products", productService.searchByName(name));
        return "list";
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "detail";
    }

}
