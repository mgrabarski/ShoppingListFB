package com.mateusz.grabarski.myshoppinglist.database.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by MGrabarski on 23.12.2017.
 */
@IgnoreExtraProperties
public class User {

    private String name;
    private String email;
    private String password;
    private long createDate;
    private String pictureUrl;
    private List<FriendRequest> friendRequests;

    public User() {
    }

    public User(String name, String email, String password, long createDate, String pictureUrl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.pictureUrl = pictureUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    @Exclude
    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<FriendRequest> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(List<FriendRequest> friendRequests) {
        this.friendRequests = friendRequests;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", friendRequests=" + friendRequests +
                '}';
    }
}
