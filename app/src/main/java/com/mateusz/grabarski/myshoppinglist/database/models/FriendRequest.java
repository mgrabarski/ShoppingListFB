package com.mateusz.grabarski.myshoppinglist.database.models;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class FriendRequest {

    private String emailWho;
    private long timestampOfSendRequest;
    private long timestampOfDenyRequest;
    private long timestampOfAcceptRequest;
    private String key;

    public FriendRequest() {
    }

    public String getEmailWho() {
        return emailWho;
    }

    public void setEmailWho(String emailWho) {
        this.emailWho = emailWho;
    }

    public long getTimestampOfSendRequest() {
        return timestampOfSendRequest;
    }

    public void setTimestampOfSendRequest(long timestampOfSendRequest) {
        this.timestampOfSendRequest = timestampOfSendRequest;
    }

    public long getTimestampOfDenyRequest() {
        return timestampOfDenyRequest;
    }

    public void setTimestampOfDenyRequest(long timestampOfDenyRequest) {
        this.timestampOfDenyRequest = timestampOfDenyRequest;
    }

    public long getTimestampOfAcceptRequest() {
        return timestampOfAcceptRequest;
    }

    public void setTimestampOfAcceptRequest(long timestampOfAcceptRequest) {
        this.timestampOfAcceptRequest = timestampOfAcceptRequest;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "emailWho='" + emailWho + '\'' +
                ", timestampOfSendRequest=" + timestampOfSendRequest +
                ", timestampOfDenyRequest=" + timestampOfDenyRequest +
                ", timestampOfAcceptRequest=" + timestampOfAcceptRequest +
                ", key='" + key + '\'' +
                '}';
    }
}
