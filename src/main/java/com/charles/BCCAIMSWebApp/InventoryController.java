package com.charles.BCCAIMSWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/add")
    public String addItem(@RequestParam String item, @RequestParam Integer quantity, @RequestParam Integer lowQuantity) {
        Inventory inventory = new Inventory();
        inventory.setItem(item);
        inventory.setQuantity(quantity);
        inventory.setLowQuantity(lowQuantity);
        inventoryRepository.save(inventory);
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

    @RequestMapping("/delete")
    public String delete() {
        return "delete";
    }
}
