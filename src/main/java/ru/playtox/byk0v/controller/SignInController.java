package ru.playtox.byk0v.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.playtox.byk0v.entity.User;
import ru.playtox.byk0v.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class SignInController {

    private boolean isError = false;

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/index", "/signin"})
    public String signIn(Model model) {
        model.addAttribute("isError", isError);
        return "signin";
    }

    @PostMapping("/signin")
    public String authorization(@RequestParam String login,
                                @RequestParam String password,
                                HttpSession httpSession,
                                Model model) {

        User userFromDb = userService.getUserByLogin(login);
        if (userFromDb == null) {
            isError = true;
            return "signin";
        }

        if ( httpSession != null ) {
            httpSession.setAttribute("user", userFromDb);
        }

        if (userFromDb.getRole().equals("user"))
            return "redirect:/shop";

        return "redirect:/admin";
    }

    @PostMapping("/signout")
    public String signOut(HttpSession httpSession) {
        if ( httpSession != null ) {
            User user = (User)httpSession.getAttribute("user");
            if (user != null)
                httpSession.removeAttribute("user");
        }
        return "redirect:/signin";
    }
}
