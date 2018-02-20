package com.mateusz.grabarski.myshoppinglist.database.models;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public class ShoppingItem {

    private long createDate;
    private int number;
    private boolean inCart;

    public ShoppingItem() {
    }

    public ShoppingItem(long createDate, int number, boolean inCart) {
        this.createDate = createDate;
        this.number = number;
        this.inCart = inCart;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingItem that = (ShoppingItem) o;

        if (createDate != that.createDate) return false;
        if (number != that.number) return false;
        return inCart == that.inCart;
    }

    @Override
    public int hashCode() {
        int result = (int) (createDate ^ (createDate >>> 32));
        result = 31 * result + number;
        result = 31 * result + (inCart ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "createDate=" + createDate +
                ", number=" + number +
                ", inCart=" + inCart +
                '}';
    }
}
