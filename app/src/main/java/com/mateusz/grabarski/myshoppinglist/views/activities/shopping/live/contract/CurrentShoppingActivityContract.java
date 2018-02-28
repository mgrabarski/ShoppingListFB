package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract;

import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;

import java.util.List;

/**
 * Created by MGrabarski on 26.02.2018.
 */

public interface CurrentShoppingActivityContract {

    interface Model {

        List<ShoppingItem> getItems();

        void setShoppingListId(String shoppingListId);

        void updateItemInDb(ShoppingItem item);
    }

    interface View {

        void updateList();
    }

    interface Presenter {

        void loadList(String shoppingListId);

        List<ShoppingItem> getShoppingItems();

        void updateList(List<ShoppingItem> items);

        void updateItem(ShoppingItem item);

        void itemChange(ShoppingItem item);
    }
}
