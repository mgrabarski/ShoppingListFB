package com.mateusz.grabarski.myshoppinglist.views.activities.shopping.live.contract.data;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.mateusz.grabarski.myshoppinglist.database.dto.ShoppingListRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.SLDatabaseReferenceListener;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Grabarski on 27.02.2018.
 */

public class CurrentListManager {

    private ShoppingListRepository mRepository;
    private CurrentListListener mListener;
    private String mOwnerEmail;
    private List<ShoppingItem> mItems;
    private String mShoppingListId;

    public CurrentListManager(String owner, ShoppingListRepository repository, CurrentListListener listener) {
        this.mOwnerEmail = owner;
        this.mRepository = repository;
        this.mListener = listener;
        this.mItems = new ArrayList<>();
    }

    public void setShoppingListId(final String shoppingListId) {
        this.mShoppingListId = shoppingListId;

        mRepository.getDatabaseReference(mOwnerEmail, new SLDatabaseReferenceListener() {
            @Override
            public void onShoppingListDatabaseReference(DatabaseReference reference) {
                reference
                        .child(shoppingListId)
                        .child("shoppingItems")
                        .addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                addItem(dataSnapshot.getValue(ShoppingItem.class));
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                                updateItem(dataSnapshot.getValue(ShoppingItem.class));
                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                                removeItem(dataSnapshot.getValue(ShoppingItem.class));
                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        });
    }

    private void addItem(ShoppingItem item) {

        for (ShoppingItem i : mItems)
            if (i.getCreateDate() == item.getCreateDate())
                return;

        mItems.add(item);

        notifyListener();
    }

    private void updateItem(ShoppingItem item) {

        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).getCreateDate() == item.getCreateDate()) {
                mItems.set(i, item);
                break;
            }
        }

        notifyListener();
    }

    private void removeItem(ShoppingItem item) {

        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).getCreateDate() == item.getCreateDate()) {
                mItems.remove(i);
                break;
            }
        }

        notifyListener();
    }

    private void notifyListener() {
        mListener.updateList(mItems);
    }

    public String getShoppingListId() {
        return mShoppingListId;
    }
}
