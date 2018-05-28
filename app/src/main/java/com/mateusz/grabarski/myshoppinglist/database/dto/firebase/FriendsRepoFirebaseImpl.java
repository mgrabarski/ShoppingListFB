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
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.GetUserListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.FriendAddedListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.FriendRequestDenied;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.GetUserFriendRequestsListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.GetUserFriendsListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.SendFriendRequestListener;
import com.mateusz.grabarski.myshoppinglist.database.models.Friend;
import com.mateusz.grabarski.myshoppinglist.database.models.FriendRequest;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

public class FriendsRepoFirebaseImpl implements FriendsRepository {

    private static final String TAG = "FriendsRepoFirebaseImpl";

    private FirebaseDatabaseLocation mFirebaseDatabaseLocation;
    private UserRepoFirebaseImpl mUserRepository;

    public FriendsRepoFirebaseImpl() {
        mFirebaseDatabaseLocation = new FirebaseDatabaseLocation();
        mUserRepository = new UserRepoFirebaseImpl();
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

        String currentLoginUser = getCurrentLoginUserEmail();

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

    @Override
    public void friendRequestDenied(FriendRequest request, FriendRequestDenied listener) {

        String currentLoginUser = getCurrentLoginUserEmail();

        mFirebaseDatabaseLocation
                .getFriendRequestDatabaseReference(currentLoginUser)
                .child(request.getKey())
                .setValue(request)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onRequestDenied(request);
                    }
                });
    }

    @Override
    public void getUserFriends(User user, GetUserFriendsListener listener) {
        mFirebaseDatabaseLocation.getFriendsDatabaseReference(user.getEmail())
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Friend friend = dataSnapshot.getValue(Friend.class);
                        mUserRepository.getUserByEmail(friend.getEmail(), new GetUserListener() {
                            @Override
                            public void onUserLoaded(User user) {
                                listener.onNewFriend(user);
                            }

                            @Override
                            public void onErrorReceived(String message) {

                            }
                        });
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        Log.d(TAG, "onChildChanged: " + dataSnapshot.toString());
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        Log.d(TAG, "onChildRemoved: " + dataSnapshot.toString());
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private String getCurrentLoginUserEmail() {
        return FirebaseAuth
                .getInstance()
                .getCurrentUser()
                .getEmail();
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
