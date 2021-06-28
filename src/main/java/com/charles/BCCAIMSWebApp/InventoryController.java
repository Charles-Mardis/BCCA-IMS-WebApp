package com.charles.BCCAIMSWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/add")
    public String addItem() {
        return "add";
    }

    @GetMapping("/list")
    public Iterable<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Inventory findItemById(@PathVariable Integer id) {
        return inventoryRepository.findItemById(id);
    }

    @GetMapping("/delete")
    public String delete() {
        return "delete";
    }

    @GetMapping("/view")
    public String view() {
        return "view";
    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String processRegistration(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassowrd = encoder.encode(user.getPassword());
        user.setPassword(encodedPassowrd);
        userRepo.save(user);
        return "process_register";
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

}
