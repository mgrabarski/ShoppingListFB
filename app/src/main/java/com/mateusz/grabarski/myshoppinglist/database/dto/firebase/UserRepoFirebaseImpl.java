package com.mateusz.grabarski.myshoppinglist.database.dto.firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.mateusz.grabarski.myshoppinglist.database.FirebaseDatabaseLocation;
import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.utils.InputFormatter;

/**
 * Created by MGrabarski on 30.12.2017.
 */

public class UserRepoFirebaseImpl implements UserRepository {

    public UserRepoFirebaseImpl() {

    }

    @Override
    public void insertUser(final User user, final CreateNewAccountListener listener) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    listener.onCreateAccountFailed(task.getException().getMessage());
                } else {
                    InputFormatter inputFormatter = new InputFormatter();

                    user.setCreateDate(System.currentTimeMillis());

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child(FirebaseDatabaseLocation.FIREBASE_USERS + inputFormatter.encodeEmail(user.getEmail()) + "/")
                            .setValue(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        listener.onCreateAccountSuccess(true, user);
                                    } else {
                                        listener.onCreateAccountFailed(task.getException().getMessage());
                                    }
                                }
                            });

                }
            }
        });
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
