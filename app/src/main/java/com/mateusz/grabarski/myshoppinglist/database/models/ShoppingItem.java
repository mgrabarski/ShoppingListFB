package com.mateusz.grabarski.myshoppinglist.database.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */
@IgnoreExtraProperties
public class ShoppingItem implements Serializable {

    private long createDate;
    private float number;
    private boolean inCart;
    private String name;
    private String whoBuy;

    public ShoppingItem() {
    }

    public static ShoppingItem getNewShoppingItem() {
        ShoppingItem item = new ShoppingItem();
        item.setCreateDate(System.currentTimeMillis());
        item.setInCart(false);
        item.setNumber(0);
        item.setWhoBuy(null);
        return item;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhoBuy() {
        return whoBuy;
    }

    public void setWhoBuy(String whoBuy) {
        this.whoBuy = whoBuy;
    }

    @Exclude
    public String getDisplayValue() {
        return name + " (" + (int) number + ")";
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "createDate=" + createDate +
                ", number=" + number +
                ", inCart=" + inCart +
                ", name='" + name + '\'' +
                ", whoBuy='" + whoBuy + '\'' +
                '}';
    }
}
