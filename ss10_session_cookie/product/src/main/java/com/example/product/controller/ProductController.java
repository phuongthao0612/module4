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
        return new Cart(); // Mỗi phiên làm việc sẽ tạo mới một giỏ hàng
    }

    // Hiển thị tất cả sản phẩm
    @GetMapping("")
    public String shop(Model model) {
        model.addAttribute("products", productService.findAll());
        return "shop"; // Trang cửa hàng
    }

    // Hiển thị form thêm sản phẩm mới
    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("product", new Product());
        return "product"; // Trang form thêm sản phẩm
    }

    // Xử lý thêm sản phẩm mới vào cửa hàng
    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("product") Product product,
                      BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return "product";
        }
        productService.addProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product added successfully");
        return "redirect:/shop"; // Quay lại trang shop
    }

    // Chi tiết sản phẩm
    @GetMapping("/product/{id}")
    public String viewProductDetail(@PathVariable int id, Model model) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "error"; // Nếu không tìm thấy sản phẩm
        }
        model.addAttribute("product", productOptional.get());
        return "detail"; // Trang chi tiết sản phẩm (detail.html)
    }

    // Thêm sản phẩm vào giỏ hàng và chuyển đến trang giỏ hàng
    @PostMapping("/product/{id}/add_cart")
    public String addToCartAndShow(@PathVariable("id") int id, @ModelAttribute Cart cart) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error"; // Nếu không tìm thấy sản phẩm
        }
        Product product = productOptional.get();
        cartService.addProductToCart(cart, product); // Thêm sản phẩm vào giỏ hàng
        return "redirect:/cart"; // Chuyển đến trang giỏ hàng
    }

    // Thêm sản phẩm vào giỏ hàng và quay lại cửa hàng
    @PostMapping("/product/{id}/add_shop")
    public String addToCartAndBackToShop(@PathVariable Integer id, @ModelAttribute Cart cart) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error"; // Nếu không tìm thấy sản phẩm
        }
        Product product = productOptional.get();
        cartService.addProductToCart(cart, product); // Thêm sản phẩm vào giỏ hàng
        return "redirect:/shop"; // Quay lại cửa hàng
    }
}