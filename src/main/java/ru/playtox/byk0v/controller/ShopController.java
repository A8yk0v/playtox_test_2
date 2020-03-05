package ru.playtox.byk0v.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.playtox.byk0v.entity.Product;
import ru.playtox.byk0v.entity.User;
import ru.playtox.byk0v.service.ProductService;
import ru.playtox.byk0v.service.ShoppingService;

import javax.servlet.http.HttpSession;

@Controller
public class ShopController {
    @Autowired
    private ShoppingService shoppingService;
    @Autowired
    private ProductService productService;

    private boolean isRenderIsBuy = false;
    private boolean isBuy = false;

    @GetMapping("/shop")
    public String shop(@RequestParam(required = false, defaultValue = "-1") long product_id,
                       @RequestParam(required = false, defaultValue = "-1") int quantity,
                       HttpSession httpSession,
                       Model model) {

        User userFromSession = (User)httpSession.getAttribute("user");
        if (userFromSession == null)
            return "redirect:/signin";

        if (product_id > 0 && quantity > 0) {
            isBuy = shoppingService.buy(userFromSession, product_id, quantity);
            isRenderIsBuy = true;
        }


        Iterable<Product> products = productService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("isRenderIsBuy", isRenderIsBuy);
        model.addAttribute("isBuy", isBuy);
        return "shop";
    }
}
