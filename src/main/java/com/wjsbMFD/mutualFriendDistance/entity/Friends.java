package com.wjsbMFD.mutualFriendDistance.entity;


import javax.persistence.*;

@Entity
@Table(name="friends")
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="friend_id")
    private int friendId;

    @Column(name="friendOne")
    private int friendOne;

    @Column(name="friendTwo")
    private int friendTwo;

    @Column(name="date_Created")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String dateCreated;

    public Friends(int friendOne, int friendTwo,String dateCreated) {
        this.friendOne = friendOne;
        this.friendTwo = friendTwo;
        this.dateCreated=dateCreated;
    }
    public Friends(){}

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
