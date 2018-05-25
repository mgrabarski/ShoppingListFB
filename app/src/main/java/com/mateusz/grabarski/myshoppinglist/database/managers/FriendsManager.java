package com.mateusz.grabarski.myshoppinglist.database.managers;

import com.google.firebase.auth.FirebaseAuth;
import com.mateusz.grabarski.myshoppinglist.database.dto.FriendsRepository;
import com.mateusz.grabarski.myshoppinglist.database.dto.firebase.FriendsRepoFirebaseImpl;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.GetUserListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.FriendAddedListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.GetUserFriendRequestsListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.GetUserFromFriendRequestListener;
import com.mateusz.grabarski.myshoppinglist.database.managers.listeners.friends.SendFriendRequestListener;
import com.mateusz.grabarski.myshoppinglist.database.models.FriendRequest;
import com.mateusz.grabarski.myshoppinglist.database.models.User;

public class FriendsManager {

    private FriendsRepository mFriendsRepository;
    private UserManager mUserManager;

    public FriendsManager() {
        mFriendsRepository = new FriendsRepoFirebaseImpl();
        mUserManager = new UserManager();
    }

    public void sendFriendRequestToUser(User user, SendFriendRequestListener listener) {
        mUserManager.getUserByEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                new GetUserListener() {
                    @Override
                    public void onUserLoaded(User currentLoginUser) {
                        mFriendsRepository.addNewRequest(currentLoginUser, user, listener);
                    }

                    @Override
                    public void onErrorReceived(String message) {

                    }
                });
    }

    public void getAllUserFriendRequests(User user, GetUserFriendRequestsListener listener) {
        mFriendsRepository.getUserFriendRequests(user, listener);
    }

    public void getUserFromFriendRequest(FriendRequest request, GetUserFromFriendRequestListener listener) {
        mUserManager.getUserByEmail(request.getEmailWho(), new GetUserListener() {
            @Override
            public void onUserLoaded(User user) {
                listener.onUser(request, user);
            }

            @Override
            public void onErrorReceived(String message) {

            }
        });
    }

    public void requestAccepted(FriendRequest request, FriendAddedListener listener) {
        mFriendsRepository.addNewFriend(request, listener);
    }
}
