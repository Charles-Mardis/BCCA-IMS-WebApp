package com.charles.BCCAIMSWebApp;

import javax.persistence.*;

@Entity
@Table(name="inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, unique = true, length=60)
    private String item;
    @Column(nullable = false, length=11)
    private Integer quantity;
    @Column(nullable = false, length=11)
    private Integer low_quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {this.quantity = quantity; }

    public Integer getLow_quantity() {
        return low_quantity;
    }

    public void setLow_quantity(Integer low_quantity) {
        this.low_quantity = low_quantity;
    }
}

