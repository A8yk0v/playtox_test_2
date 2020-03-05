package ru.playtox.byk0v.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.playtox.byk0v.entity.Product;
import ru.playtox.byk0v.entity.Purchase;
import ru.playtox.byk0v.entity.User;
import ru.playtox.byk0v.repository.PurchaseRepo;
import ru.playtox.byk0v.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private PurchaseRepo purchaseRepo;

    private boolean isRenderIsAdd = false;
    private boolean isAdd = false;
    private boolean isRenderIsDelete = false;
    private boolean isDelete = false;
    private boolean isRenderIsEdit = false;
    private boolean isEdit = false;

    @GetMapping("/admin")
    public String grudProduct(@RequestParam(required = false, defaultValue = "-1") String name,
                              @RequestParam(required = false, defaultValue = "-1") String description,
                              @RequestParam(required = false, defaultValue = "-1") int price,
                              @RequestParam(required = false, defaultValue = "-1") int quantity,
                              HttpSession httpSession,
                              Model model) {

        User userFromSession = (User)httpSession.getAttribute("user");
        if (userFromSession == null || userFromSession.getRole().equals("user") )
            return "redirect:/signin";

        addInModelAllAttributes(model);

        return "admin";
    }

    @GetMapping("/admin/listPurchase")
    public String listPurchase(Model model) {
        Iterable<Purchase> purchases = purchaseRepo.findAll();
        model.addAttribute("purchases", purchases);
        return "listPurchase";
    }

    @PostMapping("/admin/addProduct")
    public String addProduct(@RequestParam(required = false, defaultValue = "-1") String name,
                              @RequestParam(required = false, defaultValue = "-1") String description,
                              @RequestParam(required = false, defaultValue = "-1") int price,
                              @RequestParam(required = false, defaultValue = "-1") int quantity,
                              Model model) {

        if (name.equals("-1") || description.equals("-1") || price == -1 || quantity == -1) {
            isRenderIsAdd = true;
            isAdd = false;
        } else {
            isRenderIsAdd = true;
            isAdd = productService.add(new Product(name, description, price, quantity) );
        }

        addInModelAllAttributes(model);

        return "admin";
    }

    @PostMapping("/admin/deleteProduct")
    public String addProduct(@RequestParam(required = false, defaultValue = "-1") int product_id,
                             Model model) {

        if (product_id < 1) {
            isRenderIsDelete = true;
            isDelete = false;
        } else {
            isRenderIsDelete = true;
            isDelete = productService.deleteById(product_id);
        }

        addInModelAllAttributes(model);

        return "admin";
    }

    @PostMapping("/admin/editProduct")
    public String addProduct(@RequestParam(required = false, defaultValue = "-1") long product_id,
                             @RequestParam(required = false, defaultValue = "-1") String name,
                             @RequestParam(required = false, defaultValue = "-1") String description,
                             @RequestParam(required = false, defaultValue = "-1") int price,
                             @RequestParam(required = false, defaultValue = "-1") int quantity,
                             Model model) {

        if (product_id < 1 || name.equals("-1") || description.equals("-1") || price < 0 || quantity < 0) {
            isRenderIsEdit = true;
            isEdit = false;
        } else {
            isRenderIsEdit = true;
            Product product = new Product(name, description, price, quantity);
            product.setId(product_id);
            isEdit = productService.edit(product);
        }

        addInModelAllAttributes(model);

        return "admin";
    }

    private void addInModelAllAttributes(Model model) {
        Iterable<Product> products = productService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("isRenderIsAdd", isRenderIsAdd);
        model.addAttribute("isAdd", isAdd);
        model.addAttribute("isRenderIsDelete", isRenderIsDelete);
        model.addAttribute("isDelete", isDelete);
        model.addAttribute("isRenderIsEdit", isRenderIsEdit);
        model.addAttribute("isEdit", isEdit);
    }
}
