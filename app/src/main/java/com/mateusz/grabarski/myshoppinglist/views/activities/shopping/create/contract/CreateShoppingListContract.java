package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.create.contract;

import android.os.Bundle;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

import java.util.List;

/**
 * Created by Mateusz Grabarski on 20.02.2018.
 */

public interface CreateShoppingListContract {

    interface Model {

        void getCurrentUser();

        void saveList(ShoppingList mShoppingList);
    }

    interface View {

        void updateList(List<ShoppingItem> shoppingItems);

        void displayDialogForSaveShoppingList();

        void closeView();

        void displayDialogNoItemsOnList();
    }

    interface Presenter {

        void setListName(String listName);

        void setCurrentUser(User user);

        List<ShoppingItem> getShoppingList();

        void addNewShoppingItem(ShoppingItem item);

        void editItem(ShoppingItem item);

        void removeShoppingItem(ShoppingItem item);

        void saveInBundleList(Bundle outState);

        boolean canCloseActivity();

        void saveList();

        void listSaved(ShoppingList list);
    }
}
