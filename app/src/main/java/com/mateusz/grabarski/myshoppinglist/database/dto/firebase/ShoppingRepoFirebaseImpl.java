package com.mateusz.grabarski.myshoppinglist.database.dto.firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.mateusz.grabarski.myshoppinglist.database.FirebaseDatabaseLocation;
import com.mateusz.grabarski.myshoppinglist.database.dto.ShoppingListRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.DeleteShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.InsertShoppingListListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.SLDatabaseReferenceListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.shopping.UpdateListNameListener;
import com.mateusz.grabarski.myshoppinglist.database.models.ShoppingList;

/**
 * Created by Mateusz Grabarski on 22.02.2018.
 */

public class ShoppingRepoFirebaseImpl implements ShoppingListRepository {

    private FirebaseDatabaseLocation mFirebaseDatabaseLocation;

    public ShoppingRepoFirebaseImpl() {
        mFirebaseDatabaseLocation = new FirebaseDatabaseLocation();
    }

    @Override
    public void insertShoppingList(ShoppingList list, InsertShoppingListListener listener) {
        DatabaseReference reference = mFirebaseDatabaseLocation
                .getShoppingListDatabaseReference(list.getOwnerEmail())
                .push();

        list.setId(reference.getKey());

        mFirebaseDatabaseLocation
                .getShoppingListDatabaseReference(list.getOwnerEmail())
                .child(reference.getKey())
                .setValue(list);

        if (list.getId() != null)
            listener.onInsertSuccess(list);
        else
            listener.onInsertError(list);
    }

    @Override
    public void getDatabaseReference(String owner, SLDatabaseReferenceListener listener) {
        DatabaseReference reference = mFirebaseDatabaseLocation.getShoppingListDatabaseReference(owner);
        listener.onShoppingListDatabaseReference(reference);
    }

    @Override
    public void deleteShoppingList(final ShoppingList list, final DeleteShoppingListListener listener) {
        DatabaseReference reference = mFirebaseDatabaseLocation.getShoppingListDatabaseReference(list.getOwnerEmail());
        reference.child(list.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                listener.onDeleteSuccess(task.isSuccessful(), list);
            }
        });
    }

    @Override
    public void updateListName(final ShoppingList list, final UpdateListNameListener listener) {
        DatabaseReference reference =
                mFirebaseDatabaseLocation.getShoppingListDatabaseReference(list.getOwnerEmail())
                        .child(list.getId())
                        .child("listName");
        reference.setValue(list.getListName())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        listener.onUpdate(task.isSuccessful(), list);
                    }
                });
    }
}
