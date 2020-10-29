package com.wjsbMFD.mutualFriendDistance.repository;

import com.wjsbMFD.mutualFriendDistance.entity.Friends;
import com.wjsbMFD.mutualFriendDistance.entity.Users;

import java.util.List;

public interface FriendRepository {
    //find all users
    public List<Users> findAll();

    public List<Friends> findAllFriends();

    //find single user by id
    public Users findOne(int id);

    //create user
    public void insertUser(Users user);

    //delete user
    public void deleteUser(int id);

    public int lookForAlreadyPresentFriend(int friendIdOne, int friendIdTwo);

    public List<Integer> lookForAlreadyPresentFriends(int friendIdOne, int friendIdTwo);

    public void insertFriend(int friendIdOne, int friendIdTwo);

    public void deleteFriend(int friendUId);

    public List<Friends> findUsersAllFriends(int friendId);

    public List<Users> findMultipleFriends(List<Integer> distanceFriendUserList);
}
