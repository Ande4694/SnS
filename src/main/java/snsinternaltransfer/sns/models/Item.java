package snsinternaltransfer.sns.models;

import org.springframework.stereotype.Component;

@Component
public class Item {

    private int id;
    private String name;
    private double unitPrice;
    private int itemCode;

    public Item(String name, double unitPrice, int itemCode) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.itemCode = itemCode;
    }

    public Item(int id, String name, double unitPrice, int itemCode) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.itemCode = itemCode;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }
}