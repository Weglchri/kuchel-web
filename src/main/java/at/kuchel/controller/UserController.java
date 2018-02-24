package at.kuchel.controller;

import at.kuchel.service.UserService;
import at.kuchel.util.UserRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRetriever userRetriever;

    @RequestMapping(value = "/landing", method = RequestMethod.GET)
    public String listStudent(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "landing";
    }
}