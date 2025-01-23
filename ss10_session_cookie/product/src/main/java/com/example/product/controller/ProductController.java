package com.example.product.controller;

import com.example.product.model.Cart;
import com.example.product.model.Product;
import com.example.product.service.ICartService;
import com.example.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/shop")
@SessionAttributes("cart")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartService cartService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("")
    public String shop(Model model) {
        model.addAttribute("products", productService.findAll());
        return "shop";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("product") Product product,
                      BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return "product";
        }
        productService.addProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product added successfully");
        return "redirect:/shop";
    }

    @GetMapping("/product/{id}")
    public String viewProductDetail(@PathVariable int id, Model model) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
<<<<<<< HEAD
            return "error";
        }
        model.addAttribute("product", productOptional.get());
        return "detail";
    }

    @PostMapping("/product/{id}/add-cart")
    public String addToCartAndShow(@PathVariable("id") int id, @ModelAttribute Cart cart) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error";
        }
        Product product = productOptional.get();
        cartService.addProductToCart(cart, product);
        return "redirect:/cart";
    }

    @PostMapping("/product/{id}/add-shop")
=======
            return "error"; 
        }
        model.addAttribute("product", productOptional.get());
        return "detail"; 
    }
    
    @PostMapping("/product/{id}/add_cart")
    public String addToCartAndShow(@PathVariable("id") int id, @ModelAttribute Cart cart) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error"; 
        }
        Product product = productOptional.get();
        cartService.addProductToCart(cart, product); 
        return "redirect:/cart";
    }

    @PostMapping("/product/{id}/add_shop")
>>>>>>> ca81c6b84077d5dde03912d6b24b850b9b51ec3e
    public String addToCartAndBackToShop(@PathVariable Integer id, @ModelAttribute Cart cart) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error";
        }
        Product product = productOptional.get();
        cartService.addProductToCart(cart, product);
        return "redirect:/shop";
    }
}
