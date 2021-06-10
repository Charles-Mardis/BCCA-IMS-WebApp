package com.charles.BCCAIMSWebApp;

import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

    Inventory findItemById(Integer id);
}
