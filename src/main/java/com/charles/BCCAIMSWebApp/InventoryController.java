package com.charles.BCCAIMSWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Controller
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("inventory", new Inventory());
        return "add";
    }

    @PostMapping("/add_success")
    public String processItem(Inventory inventory, @AuthenticationPrincipal CustomUserDetail userDetail, Model model) {
        System.out.println(inventory.getItem());
        System.out.println(inventory.getQuantity());
        System.out.println(inventory.getLow_quantity());

        inventoryRepository.save(inventory);
        return "/add_success";
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
    @GetMapping("/view")
    public String listInventory(Model model) {
        List<Inventory> listInventory = (List<Inventory>) inventoryRepository.findAll();
        model.addAttribute("listInventory", listInventory);
        return "view";
    }

    @GetMapping("/inventory_delete/{id}")
    public String inventory_delete(@PathVariable(name ="id")Long id, Inventory inventory) {
        Optional<Inventory> oldInventory = inventoryRepository.findById(inventory.getId());
        oldInventory.ifPresent(value -> inventoryRepository.delete(oldInventory.get()));
        return "redirect:/view";
    }

    @GetMapping("/edit_inventory/{id}")
    public ModelAndView editInventory(@PathVariable(name ="id")Integer id) {
        ModelAndView mav =  new ModelAndView("edit_inventory");
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        mav.addObject("inventory",inventory);
        return mav;
    }

    @PostMapping("/update")
    public String inventory_info(Inventory inventory){
        Optional<Inventory> oldInventory = inventoryRepository.findById(inventory.getId());
        if (oldInventory != null) {
            oldInventory.get().setId(inventory.getId());
            oldInventory.get().setItem(inventory.getItem());
            oldInventory.get().setQuantity(inventory.getQuantity());
            oldInventory.get().setLow_quantity(inventory.getLow_quantity());
            inventoryRepository.save(oldInventory.get());
        }
        return "redirect:/view";
    }
    @GetMapping("/low_inventory")
    public String lowInventory(Long id, Integer quantity, Integer low_quantity, Model model) {
        List<Inventory> listInventory = inventoryRepository.getAllItems();
        listInventory.removeIf(low -> low.getQuantity() > low.getLow_quantity() );
        model.addAttribute("listInventory", listInventory);
        return "low_inventory";
    }

}
