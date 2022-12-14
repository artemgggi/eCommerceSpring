package com.artemgggi.webshop.controller;

import com.artemgggi.webshop.dto.ProductRepository;
import com.artemgggi.webshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/productsList")
    public String showProducts(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        return "/productsList";
    }

    @GetMapping("/")
    public String showAddProducts() {
        return "/addProduct";
    }

    @PostMapping("/addProduct")
    public String saveProduct(@RequestParam("pname") String name,
                              @RequestParam("pprice") int price,
                              @RequestParam("pdescription") String description) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setDescription(description);
        productRepository.save(p);
        return "redirect:/productsList";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/productsList";
    }

    @PostMapping("/changePname")
    public String changePname(@RequestParam("id") Long id,
                              @RequestParam("newPname") String name) {
        Product p = new Product();
        p = productRepository.findById(id).get();
        p.setName(name);
        productRepository.save(p);
        return "redirect:/productsList";
    }

    @PostMapping("/changePprice")
    public String changePprice(@RequestParam("id") Long id,
                               @RequestParam("newPprice") int price) {
        Product p = new Product();
        p = productRepository.findById(id).get();
        p.setPrice(price);
        productRepository.save(p);
        return "redirect:/productsList";
    }

    @PostMapping("/changePdescription")
    public String changePdescriprion(@RequestParam("id") Long id,
                               @RequestParam("newPdescription") String description) {
        Product p = new Product();
        p = productRepository.findById(id).get();
        p.setDescription(description);
        productRepository.save(p);
        return "redirect:/productsList";
    }

}