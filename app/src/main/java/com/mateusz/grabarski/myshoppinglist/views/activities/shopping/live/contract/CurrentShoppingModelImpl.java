package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mateusz.grabarski.myshoppinglist.database.dto.ShoppingListRepository;
import com.mateusz.grabarski.myshoppinglist.database.dto.firebase.ShoppingRepoFirebaseImpl;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.SLDatabaseReferenceListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.UpdateShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract.data.CurrentListListener;
import com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract.data.CurrentListManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MGrabarski on 27.02.2018.
 */

public class CurrentShoppingModelImpl implements CurrentShoppingActivityContract.Model, CurrentListListener {

    private CurrentShoppingActivityContract.Presenter mPresenter;
    private ShoppingListRepository mRepository;
    private List<ShoppingItem> mItems;
    private CurrentListManager mCurrentListManager;
    private String mOwnerEmail;

    public CurrentShoppingModelImpl(CurrentShoppingActivityContract.Presenter presenter, String owner) {
        this.mPresenter = presenter;
        this.mRepository = new ShoppingRepoFirebaseImpl();
        this.mOwnerEmail = owner;
        mItems = new ArrayList<>();
        mCurrentListManager = new CurrentListManager(owner, mRepository, this);
    }

    @Override
    public List<ShoppingItem> getItems() {
        return mItems;
    }

    @Override
    public void setShoppingListId(String shoppingListId) {
        mCurrentListManager.setShoppingListId(shoppingListId);
    }

    @Override
    public void updateItemInDb(final ShoppingItem item) {
        mRepository.getDatabaseReference(mOwnerEmail, new SLDatabaseReferenceListener() {
            @Override
            public void onShoppingListDatabaseReference(DatabaseReference reference) {
                reference
                        .child(mCurrentListManager.getShoppingListId())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                ShoppingList list = dataSnapshot.getValue(ShoppingList.class);

                                if (list != null && list.getShoppingItems() != null) {
                                    for (int i = 0; i < list.getShoppingItems().size(); i++) {
                                        if (list.getShoppingItems().get(i).getCreateDate() == item.getCreateDate()) {
                                            list.getShoppingItems().set(i, item);
                                            break;
                                        }
                                    }

                                    mRepository.updateList(list, new UpdateShoppingListListener() {
                                        @Override
                                        public void onUpdate(boolean success, ShoppingList list) {
                                            // TODO: 28.02.2018 if !success
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        });
    }

    @Override
    public void updateList(List<ShoppingItem> items) {
        mItems.clear();
        mItems.addAll(items);
        mPresenter.updateList(mItems);
    }
}
