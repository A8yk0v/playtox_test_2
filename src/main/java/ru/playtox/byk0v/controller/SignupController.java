package ru.playtox.byk0v.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.playtox.byk0v.entity.User;
import ru.playtox.byk0v.service.UserService;

import java.util.Map;

@Controller
public class SignupController {
    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String main() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam(required = false, defaultValue = "") String login,
                         @RequestParam(required = false, defaultValue = "") String password,
                         Map<String, Object> model) {

        if (login.equals("") || password.equals(""))
            return "signup";

        User user = new User(login, password, "user");
        userService.save(user);

        Iterable<User> users = userService.getAll();
        model.put("users", users);

        return "signin";
    }
}
