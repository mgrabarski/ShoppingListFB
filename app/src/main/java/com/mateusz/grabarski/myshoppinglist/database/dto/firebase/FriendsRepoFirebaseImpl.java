package com.mateusz.grabarski.myshoppinglist.database.dto.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mateusz.grabarski.myshoppinglist.database.FirebaseDatabaseLocation;
import com.mateusz.grabarski.myshoppinglist.database.dto.FriendsRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.FriendAddedListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.GetUserFriendRequestsListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.SendFriendRequestListener;
import com.mateusz.grabarski.myshoppinglist.database.models.Friend;
import com.mateusz.grabarski.myshoppinglist.database.models.FriendRequest;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

public class FriendsRepoFirebaseImpl implements FriendsRepository {

    private static final String TAG = "FriendsRepoFirebaseImpl";

    private FirebaseDatabaseLocation mFirebaseDatabaseLocation;

    public FriendsRepoFirebaseImpl() {
        mFirebaseDatabaseLocation = new FirebaseDatabaseLocation();
    }

    @Override
    public void addNewRequest(User requesting, User receiver, SendFriendRequestListener listener) {
        mFirebaseDatabaseLocation.getFriendRequestDatabaseReference(receiver.getEmail())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            createFriendRequest(requesting, receiver, listener);
                        } else {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                FriendRequest request = ds.getValue(FriendRequest.class);

                                if (request.getEmailWho().equals(requesting.getEmail())) {
                                    listener.requestWasSend();
                                    return;
                                }
                            }

                            createFriendRequest(requesting, receiver, listener);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void getUserFriendRequests(User user, GetUserFriendRequestsListener listener) {
        mFirebaseDatabaseLocation.getFriendRequestDatabaseReference(user.getEmail())
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        listener.onNewFriendRequest(dataSnapshot.getValue(FriendRequest.class));
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        listener.onFriendRequestUpdate(dataSnapshot.getValue(FriendRequest.class));
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        listener.onFriendRequestDeleted(dataSnapshot.getValue(FriendRequest.class));
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void addNewFriend(FriendRequest request, FriendAddedListener listener) {
        Log.d(TAG, "addNewFriend: " + request);

        String currentLoginUser = FirebaseAuth
                .getInstance()
                .getCurrentUser()
                .getEmail();

        mFirebaseDatabaseLocation
                .getFriendRequestDatabaseReference(currentLoginUser)
                .child(request.getKey())
                .setValue(request)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        createNewFriendRelation(currentLoginUser, request, listener);
                    }
                });
    }

    private void createNewFriendRelation(String currentLoginUser, FriendRequest request, FriendAddedListener listener) {
        Friend friendForCurrentLoginUser = getFriend(request.getEmailWho(), System.currentTimeMillis());

        DatabaseReference currentLoginUserFriendDatabaseReference = getFriendPushedDatabaseReference(currentLoginUser);

        friendForCurrentLoginUser.setKey(currentLoginUserFriendDatabaseReference.getKey());

        currentLoginUserFriendDatabaseReference.setValue(friendForCurrentLoginUser)
                .addOnCompleteListener(loginUserTask -> {
                    Friend friendForRequestingUser = getFriend(currentLoginUser,
                            friendForCurrentLoginUser.getCreateDate());

                    DatabaseReference requestingUserFriendDatabaseReference =
                            getFriendPushedDatabaseReference(request.getEmailWho());

                    friendForRequestingUser.setKey(requestingUserFriendDatabaseReference.getKey());

                    requestingUserFriendDatabaseReference
                            .setValue(friendForRequestingUser)
                            .addOnCompleteListener(requestingCompleteTask -> listener.onFriendAdded());
                });
    }

    private DatabaseReference getFriendPushedDatabaseReference(String userEmail) {
        return mFirebaseDatabaseLocation
                .getFriendsDatabaseReference(userEmail)
                .push();
    }

    @NonNull
    private Friend getFriend(String email, long createDate) {
        Friend friendForCurrentLoginUser = new Friend();
        friendForCurrentLoginUser.setEmail(email);
        friendForCurrentLoginUser.setCreateDate(createDate);
        return friendForCurrentLoginUser;
    }

    private void createFriendRequest(User requesting, User receiver, SendFriendRequestListener listener) {
        FriendRequest request = new FriendRequest();
        request.setEmailWho(requesting.getEmail());
        request.setTimestampOfSendRequest(System.currentTimeMillis());

        DatabaseReference databaseReference = mFirebaseDatabaseLocation
                .getFriendRequestDatabaseReference(receiver.getEmail())
                .push();

        request.setKey(databaseReference.getKey());

        databaseReference.setValue(request)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.successRequestSend();
                    } else {
                        listener.failedRequestSend(task.getException().getMessage());
                    }
                });
    }
}
