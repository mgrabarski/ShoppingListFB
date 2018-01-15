package com.mateusz.grabarski.myshoppinglist.database.dto.firebase;

import android.support.annotation.NonNull;

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
import com.google.firebase.database.ValueEventListener;
import com.mateusz.grabarski.myshoppinglist.database.FirebaseDatabaseLocation;
import com.mateusz.grabarski.myshoppinglist.database.dto.UserRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CreateNewAccountListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.CurrentLoginUserListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginByGoogleListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.LoginListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.ResetPasswordListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.UpdateUserListener;
import com.mateusz.grabarski.myshoppinglist.database.models.User;
import com.mateusz.grabarski.myshoppinglist.utils.InputFormatter;

/**
 * Created by MGrabarski on 30.12.2017.
 */

public class UserRepoFirebaseImpl implements UserRepository {

    private FirebaseDatabaseLocation mFirebaseDatabaseLocation;

    public UserRepoFirebaseImpl() {
        mFirebaseDatabaseLocation = new FirebaseDatabaseLocation();
    }

    @Override
    public void insertUser(final User user, final CreateNewAccountListener listener) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    listener.onCreateAccountFailed(task.getException().getMessage());
                } else {
                    user.setCreateDate(System.currentTimeMillis());

                    mFirebaseDatabaseLocation.getUserDatabaseReference(user.getEmail())
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
    public void updateUser(User user, final UpdateUserListener listener) {
        mFirebaseDatabaseLocation.getUserDatabaseReference(user.getEmail())
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            listener.onUpdateSuccess();
                        } else {
                            listener.onUpdateFailed(task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void getUserByEmail(String email, CurrentLoginUserListener listener) {

    }

    @Override
    public void loginUser(final String email, String password, final LoginListener loginListener) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                            loginListener.onLoginFailed(task.getException().getMessage());
                        else {
                            mFirebaseDatabaseLocation.getUserDatabaseReference(email)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
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
    public void loginUserByGoogle(final GoogleSignInAccount account, final LoginByGoogleListener listener) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                            final User user = new User(firebaseUser.getDisplayName(), firebaseUser.getEmail(), null, System.currentTimeMillis(), account.getPhotoUrl().getPath());

                            mFirebaseDatabaseLocation.getUserDatabaseReference(user.getEmail())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue() == null) {
                                                mFirebaseDatabaseLocation.getUserDatabaseReference(user.getEmail())
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
                                                mFirebaseDatabaseLocation.getUserDatabaseReference(user.getEmail())
                                                        .addListenerForSingleValueEvent(new ValueEventListener() {
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

    @Override
    public void getCurrentLoginUser(final CurrentLoginUserListener listener) {
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        if (userEmail != null) {

            InputFormatter inputFormatter = new InputFormatter();

            String encodeEmail = inputFormatter.encodeEmail(userEmail);

            mFirebaseDatabaseLocation.getUserDatabaseReference(encodeEmail)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            listener.onCurrentLoginUserLoaded(dataSnapshot.getValue(User.class));
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            listener.onErrorReceived(databaseError.getMessage());
                        }
                    });
        }
    }
}
