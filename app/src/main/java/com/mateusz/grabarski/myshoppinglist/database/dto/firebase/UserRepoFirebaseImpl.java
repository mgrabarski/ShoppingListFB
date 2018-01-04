package com.mateusz.grabarski.myshoppinglist.database.dto.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.mateusz.grabarski.myshoppinglist.database.FirebaseDatabaseLocation;
import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginByGoogleListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.ResetPasswordListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.utils.InputFormatter;
import com.mateusz.grabarski.myshoppinglist.utils.InputValidator;
import com.mateusz.grabarski.myshoppinglist.views.activities.login.LoginActivity;

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
                                        listener.onCreateAccountSuccess(user);
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

    @Override
    public void loginUser(final String email, String password, final LoginListener loginListener) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful())
                    loginListener.onLoginFailed(task.getException().getMessage());
                else {
                    InputFormatter inputFormatter = new InputFormatter();

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child(FirebaseDatabaseLocation.FIREBASE_USERS + inputFormatter.encodeEmail(email));
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            loginListener.onLoginSuccess(user);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public void sendResetPasswordEmail(String email, final ResetPasswordListener listener) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    listener.onSendSuccess();
                } else {
                    listener.onSendFailed(task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void loginUserByGoogle(GoogleSignInAccount account, final LoginByGoogleListener listener) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                            final User user = new User(firebaseUser.getDisplayName(), firebaseUser.getEmail(), null, System.currentTimeMillis());
                            final InputFormatter inputFormatter = new InputFormatter();

                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                            databaseReference.child(FirebaseDatabaseLocation.FIREBASE_USERS + inputFormatter.encodeEmail(user.getEmail()));
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() == null) {
                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                                        databaseReference.child(FirebaseDatabaseLocation.FIREBASE_USERS + inputFormatter.encodeEmail(user.getEmail()))
                                                .setValue(user)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            listener.onLoginSuccess(user);
                                                        } else {
                                                            listener.onLoginFailed(task.getException().getMessage());
                                                        }
                                                    }
                                                });
                                    } else {
                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                                        databaseReference.child(FirebaseDatabaseLocation.FIREBASE_USERS + inputFormatter.encodeEmail(user.getEmail()));
                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                User userFromDatabase = dataSnapshot.getValue(User.class);
                                                listener.onLoginSuccess(userFromDatabase);
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                listener.onLoginFailed(databaseError.getMessage());
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    listener.onLoginFailed(databaseError.getMessage());
                                }
                            });
                        } else {
                            listener.onLoginFailed(task.getException().getMessage());
                        }
                    }
                });
    }
}
