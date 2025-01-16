package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.IService;
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
    private IService productService;

    @GetMapping("")
    public String showForm(@RequestParam(value = "name", required = false) String name, Model model) {
        List<Product> products;
        if (name != null && !name.isEmpty()) {
            products = productService.searchByName(name);
        } else {
            products = productService.getAll();
        }
        if (products.isEmpty()) {
            model.addAttribute("message", "No products found");
        }
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

    @GetMapping("/{id}/update")
    public String editFormUpdate(@PathVariable("id") int id, Model model) {
        Product product = productService.getById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "update";
        }
        return "redirect:/products";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") int id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        product.setId(id);
        productService.update(product);
        redirectAttributes.addFlashAttribute("message", "Product updated");
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Product product = productService.getById(id);
        if (product != null) {
            productService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Product deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Product not found.");
        }
        return "redirect:/products";
    }

    @GetMapping("/{id}/detail")
    public String detail(@PathVariable("id") int id, Model model) {
        Product product = productService.getById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "detail";
        }
        return "redirect:/products";
    }

}
