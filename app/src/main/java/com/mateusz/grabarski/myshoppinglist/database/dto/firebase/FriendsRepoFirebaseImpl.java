package com.mateusz.grabarski.myshoppinglist.database.dto.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mateusz.grabarski.myshoppinglist.database.FirebaseDatabaseLocation;
import com.mateusz.grabarski.myshoppinglist.database.dto.FriendsRepository;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.SendFriendRequestListener;
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
