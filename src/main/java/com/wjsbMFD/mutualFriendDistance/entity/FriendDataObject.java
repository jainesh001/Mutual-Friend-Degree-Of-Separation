package com.wjsbMFD.mutualFriendDistance.entity;


public class FriendDataObject {


    private int friendId;

    private int friendOne;

    private int friendTwo;

    private String dateCreated;

    public FriendDataObject(int friendOne, int friendTwo, String dateCreated) {
        this.friendOne = friendOne;
        this.friendTwo = friendTwo;
        this.dateCreated=dateCreated;
    }
    public FriendDataObject(){}

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public int getFriendOne() {
        return friendOne;
    }

    public void setFriendOne(int friendOne) {
        this.friendOne = friendOne;
    }

    public int getFriendTwo() {
        return friendTwo;
    }

    public void setFriendTwo(int friendTwo) {
        this.friendTwo = friendTwo;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "friendId=" + friendId +
                ", friendOne=" + friendOne +
                ", friendTwo=" + friendTwo +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }
}
