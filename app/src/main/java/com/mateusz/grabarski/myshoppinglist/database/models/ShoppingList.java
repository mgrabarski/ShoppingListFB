package com.mateusz.grabarski.myshoppinglist.database.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public class ShoppingList {

    private long createDate;
    private long lastChange;
    private String id;
    private String listName;
    private String ownerEmail;
    private List<ShoppingItem> shoppingItems;

    public ShoppingList() {
    }

    public static ShoppingList getNewShoppingList() {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setCreateDate(System.currentTimeMillis());
        shoppingList.setLastChange(0);
        shoppingList.setShoppingItems(new ArrayList<ShoppingItem>());
        return shoppingList;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getLastChange() {
        return lastChange;
    }

    public void setLastChange(long lastChange) {
        this.lastChange = lastChange;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingList that = (ShoppingList) o;

        if (createDate != that.createDate) return false;
        if (lastChange != that.lastChange) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (listName != null ? !listName.equals(that.listName) : that.listName != null)
            return false;
        if (ownerEmail != null ? !ownerEmail.equals(that.ownerEmail) : that.ownerEmail != null)
            return false;
        return shoppingItems != null ? shoppingItems.equals(that.shoppingItems) : that.shoppingItems == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (createDate ^ (createDate >>> 32));
        result = 31 * result + (int) (lastChange ^ (lastChange >>> 32));
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (listName != null ? listName.hashCode() : 0);
        result = 31 * result + (ownerEmail != null ? ownerEmail.hashCode() : 0);
        result = 31 * result + (shoppingItems != null ? shoppingItems.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "createDate=" + createDate +
                ", lastChange=" + lastChange +
                ", id='" + id + '\'' +
                ", listName='" + listName + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", shoppingItems=" + shoppingItems +
                '}';
    }
}
