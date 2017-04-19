package com.hiepscar.baitaplon;

/**
 * Created by hoanghiep on 11/9/16.
 */
public class Item {
    private String name;
    private int price;
    private int quantity;

    public Item(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public void add(int  amout){
        quantity+=amout;
    }
    public void sub(int amout){
        quantity-=amout;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int setQuantity(int quantity) {
        this.quantity -= quantity;
        if (this.quantity>0){
            return 0;
        }else if (this.quantity==0){
            return 1;
        }
        return 2;
    }

    @Override
    public String toString() {
        return name+"_"+price+"_"+quantity;
    }
}
