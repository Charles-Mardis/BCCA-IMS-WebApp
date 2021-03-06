package com.charles.BCCAIMSWebApp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {
    @Query("SELECT u FROM Inventory u WHERE u.id =?1")
    Inventory findItemById(Integer id);

    @Query("SELECT u FROM Inventory u")
    public List<Inventory> getAllItems();
}
