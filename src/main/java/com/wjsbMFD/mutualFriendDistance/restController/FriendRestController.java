package com.wjsbMFD.mutualFriendDistance.restController;

import com.wjsbMFD.mutualFriendDistance.entity.Friends;
import com.wjsbMFD.mutualFriendDistance.entity.Users;
import com.wjsbMFD.mutualFriendDistance.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mutualFriendDistance/api")
public class FriendRestController {

    @Autowired
    private FriendService friendService;



    @GetMapping("/users")
    public List<Users> findAll(){
        return friendService.findAll();
    }

    @GetMapping("userById/{userId}")
    public Users getUser(@PathVariable("userId") int userId){
        Users users=friendService.findById(userId);
        if(users==null){
            throw new RuntimeException("User id not found-"+ userId);
        }
        return users;
    }

    //Create User
    @PostMapping("/createUser")
    public Users getUser(@RequestBody Users user){
        friendService.createUser(user);
        return user;
    }

    @GetMapping("/friends")
    public List<Friends> findAllFriends(){
        return friendService.findAllFriends();
    }

    //Add Friend
    @PostMapping("/addFriend/{friendIdOne}/{friendIdTwo}")
    public void addFriend(@PathVariable("friendIdOne") int friendIdOne,@PathVariable("friendIdTwo") int friendIdTwo ){
        friendService.addFriend(friendIdOne,friendIdTwo);
    }

    //Remove Friend
    @DeleteMapping("/removeFriend/{friendIdOne}/{friendIdTwo}")
    public void removeFriend(@PathVariable("friendIdOne") int friendIdOne,@PathVariable("friendIdTwo") int friendIdTwo ){
        friendService.removeFriend(friendIdOne,friendIdTwo);
    }

    @GetMapping("/userFriends/{friendId}")
    public List<Users> findAllFriends(@PathVariable("friendId") int friendId){
        return friendService.findUsersAllFriends(friendId);
    }

    @GetMapping("/userFriendsAtDistance/{friendId}/{distance}")
    public List<Users> findFriendsAtDistance(@PathVariable("friendId") int friendId,@PathVariable("distance") int distance){
        return friendService.findUsersFriendsAtDistance(friendId,distance);
    }

    @GetMapping("/")
    public String one(){
        return "Hello";
    }
}
