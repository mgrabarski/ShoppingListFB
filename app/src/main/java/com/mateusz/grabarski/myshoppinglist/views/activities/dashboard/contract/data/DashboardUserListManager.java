package com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.contract.data;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.mateusz.grabarski.myshoppinglist.database.dto.ShoppingListRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.SLDatabaseReferenceListener;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;
import com.mateusz.grabarski.myshoppinglist.views.activities.dashboard.contract.data.listener.DashboardListListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MGrabarski on 25.02.2018.
 */

public class DashboardUserListManager {

    private ShoppingListRepository mRepository;
    private DashboardListListener mListener;
    private List<ShoppingList> mList;

    public DashboardUserListManager(String owner, ShoppingListRepository repository, DashboardListListener listener) {
        this.mRepository = repository;
        this.mListener = listener;
        this.mList = new ArrayList<>();

        mRepository.getDatabaseReference(owner, new SLDatabaseReferenceListener() {
            @Override
            public void onShoppingListDatabaseReference(DatabaseReference reference) {
                reference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        addList(dataSnapshot.getValue(ShoppingList.class));
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        updateList(dataSnapshot.getValue(ShoppingList.class));
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        deleteList(dataSnapshot.getValue(ShoppingList.class));
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

    private void addList(ShoppingList list) {

        for (ShoppingList currentList : mList)
            if (currentList.getId().equals(list.getId()))
                return;

        mList.add(list);

        notifyListener();
    }

    private void deleteList(ShoppingList list) {

        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId().equals(list.getId())) {
                mList.set(i, list);
                break;
            }
        }

        notifyListener();
    }

    private void updateList(ShoppingList list) {

        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId().equals(list.getId())) {
                mList.remove(i);
                break;
            }
        }

        notifyListener();
    }

    private void notifyListener() {
        mListener.updateUserLists(mList);
    }
}
