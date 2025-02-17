package com.example.thi.controller;

import com.example.thi.model.Product;
import com.example.thi.model.ProductType;
import com.example.thi.service.IProductService;
import com.example.thi.service.IProductTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductTypeService productTypeService;

    @GetMapping
    public String showAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) Integer typeId,
            @RequestParam(required = false, defaultValue = "") String name,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products;
        if (typeId != null) {
            products = productService.getProductsByType(typeId, pageable);
        } else {
            products = productService.getProducts(pageable);
        }
        List<ProductType> productTypes = productTypeService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("productTypes", productTypes);
        model.addAttribute("selectedType", typeId);
        model.addAttribute("name", name);
        return "product/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("productTypes", productTypeService.getAll());
        return "product/form";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("productTypes", productTypeService.getAll());
            return "product/form";
        }

        productService.addNew(product);
        redirectAttributes.addFlashAttribute("message", "Sản phẩm đã được thêm thành công");
        return "redirect:/products";
    }


    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        Product product = productService.getById(id);
        if (product != null) {
            productService.delete(product);
            redirectAttributes.addFlashAttribute("message", "Sản phẩm đã được xóa thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sản phẩm!");
        }
        return "redirect:/products";
    }


}
