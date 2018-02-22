package com.mateusz.grabarski.myshoppinglist.database.models;

import java.io.Serializable;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public class ShoppingItem implements Serializable {

    private long createDate;
    private float number;
    private boolean inCart;
    private String name;

    public ShoppingItem() {
    }

    public static ShoppingItem getNewShoppingItem() {
        ShoppingItem item = new ShoppingItem();
        item.setCreateDate(System.currentTimeMillis());
        item.setInCart(false);
        item.setNumber(0);
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

    public String getDisplayValue() {
        return name + " (" + number + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingItem that = (ShoppingItem) o;

        if (createDate != that.createDate) return false;
        if (Float.compare(that.number, number) != 0) return false;
        if (inCart != that.inCart) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (createDate ^ (createDate >>> 32));
        result = 31 * result + (number != +0.0f ? Float.floatToIntBits(number) : 0);
        result = 31 * result + (inCart ? 1 : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "createDate=" + createDate +
                ", number=" + number +
                ", inCart=" + inCart +
                ", name='" + name + '\'' +
                '}';
    }
}
