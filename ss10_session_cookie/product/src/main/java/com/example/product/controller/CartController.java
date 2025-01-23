package com.example.product.controller;

import com.example.product.model.Cart;
import com.example.product.model.Product;
import com.example.product.service.ICartService;
import com.example.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public String viewCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("cart", cart);
        model.addAttribute("totalPayment", cartService.calculateTotalPayment(cart));
        model.addAttribute("productQuantity", cartService.countProductQuantityInCart(cart));
        return "cart";
    }

    @PostMapping("/product/{productId}/add")
    public String addProductToCart(@PathVariable("productId") int productId, @ModelAttribute("cart") Cart cart) {
        Optional<Product> productOptional = productService.findById(productId);
        if (productOptional.isPresent()) {
            cartService.addProductToCart(cart, productOptional.get());
        }
        return "redirect:/cart";
    }

    @PostMapping("/{productId}/update")
    public String updateProductQuantity(@PathVariable("productId") int productId,
                                        @RequestParam("quantity") int quantity,
                                        @ModelAttribute("cart") Cart cart,
                                        RedirectAttributes redirectAttributes) {
        Optional<Product> productOptional = productService.findById(productId);
        if (productOptional.isPresent()) {
            cartService.updateProductQuantityInCart(cart, productOptional.get(), quantity);
            redirectAttributes.addFlashAttribute("message", "Product quantity updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Product not found!");
        }
        return "redirect:/cart";
    }

    @PostMapping("/product/{productId}/delete")
    public String deleteProduct(@PathVariable("productId") int productId,
                                @ModelAttribute("cart") Cart cart) {
        Optional<Product> productOptional = productService.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            cart.getProducts().remove(product);
        }
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String deleteAllProducts(@ModelAttribute("cart") Cart cart, RedirectAttributes redirectAttributes) {
        if (cart.getProducts().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Cart is already empty.");
        } else {
            cart.getProducts().clear();
            redirectAttributes.addFlashAttribute("message", "All products removed from cart!");
        }
        return "redirect:/cart";
    }
}
