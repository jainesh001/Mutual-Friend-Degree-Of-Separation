package com.wjsbMFD.mutualFriendDistance.service;

import com.wjsbMFD.mutualFriendDistance.entity.Friends;
import com.wjsbMFD.mutualFriendDistance.entity.Users;

import java.util.List;


public interface FriendService {

    public List<Users> findAll();

    public List<Friends> findAllFriends();


    public Users findById(int id);

    //Create User
    public void createUser(Users user);

    public void deleteById(int id);

    public void addFriend(int friendIdOne, int friendIdTwo);

    public void removeFriend(int friendIdOne, int friendIdTwo);

    //find userfriends
    public List<Users> findUsersAllFriends(int friendId);

    List<Users> findUsersFriendsAtDistance(int friendId, int distance);


    //add Friend
    //public void addFriend(int userId);

    //remove Friend

    //for provided distance available friends;
}
