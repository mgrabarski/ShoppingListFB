package com.mateusz.grabarski.myshoppinglist.database.models;

public class Friend {

    private String email;
    private long createDate;
    private String key;

    public Friend() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "email='" + email + '\'' +
                ", createDate=" + createDate +
                ", key='" + key + '\'' +
                '}';
    }
}
