package com.wjsbMFD.mutualFriendDistance.service;

import com.wjsbMFD.mutualFriendDistance.entity.Friends;
import com.wjsbMFD.mutualFriendDistance.entity.Users;
import com.wjsbMFD.mutualFriendDistance.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public List<Users> findAll() {
        return friendRepository.findAll();
    }

    @Override
    public List<Friends> findAllFriends() {
        return friendRepository.findAllFriends();
    }

    @Override
    @Transactional
    public Users findById(int id) {
        return friendRepository.findOne(id);
    }

    @Override
    public void createUser(Users user) {
        friendRepository.insertUser(user);
    }

    @Override
    public void deleteById(int id) {
        friendRepository.deleteUser(id);
    }

    @Override
    public void addFriend(int friendIdOne, int friendIdTwo) {
        //Both Id present in User table
        Users first=friendRepository.findOne(friendIdOne);
        Users Second=friendRepository.findOne(friendIdTwo);

        if(first!=null && Second!=null){

            //select friend_id from friends where ((friendOne=:friendIdOne  && friendTwo=:friendIdTwo) ||(friendOne=:friendIdTwo  && friendTwo=:friendIdOne)) LIMIT 1";
            int friendId=friendRepository.lookForAlreadyPresentFriend(friendIdOne,friendIdTwo);
            if(friendId==0){
                friendRepository.insertFriend(friendIdOne,friendIdTwo);
            }else{
                throw new RuntimeException("Friend AlreadyPresent for friendIdOne-"+friendIdOne+" friendIdTwo-"+friendIdTwo);
            }
        }else{
            throw new RuntimeException("User id not found- cannot add Friend");
        }


    }
    @Override
    public void removeFriend(int friendIdOne, int friendIdTwo) {
        //Both Id present in User table
        Users first=friendRepository.findOne(friendIdOne);
        Users Second=friendRepository.findOne(friendIdTwo);

        if(first!=null && Second!=null){

            //select friend_id from friends where ((friendOne=:friendIdOne  && friendTwo=:friendIdTwo) ||(friendOne=:friendIdTwo  && friendTwo=:friendIdOne)) LIMIT 1";
            List<Integer> friendUIdList=friendRepository.lookForAlreadyPresentFriends(friendIdOne,friendIdTwo);
            if(friendUIdList!=null && !friendUIdList.isEmpty()) {
                for (int friendUId : friendUIdList) {
                    friendRepository.deleteFriend(friendUId);
                }
            }else{
                throw new RuntimeException("Cannot Remove as a friend"+friendIdOne+" friendIdTwo-"+friendIdTwo);
            }
        }else{
            throw new RuntimeException("User id not found- cannot remove Friend");
        }


    }

    @Override
    public List<Users> findUsersAllFriends(int friendId) {
        Users first=friendRepository.findOne(friendId);
        if(first!=null){
         //   "select friendOne,friendTwo from friends where friendOne=? or friendTwo=?";
           List<Friends> friendsList= friendRepository.findUsersAllFriends(friendId);
           List<Users> userFriendList=new ArrayList<>();
           for(Friends friends:friendsList ){
               if(friends.getFriendOne()==friendId){
                   userFriendList.add (friendRepository.findOne(friends.getFriendTwo()));
               }else if(friends.getFriendTwo()==friendId){
                   userFriendList.add (friendRepository.findOne(friends.getFriendOne()));
               }
           }
           return userFriendList;
        }else{
            throw new RuntimeException("User id not found- cannot find friend");
        }
    }

    @Override
    public List<Users> findUsersFriendsAtDistance(int friendId, int distance) {
        Users first=friendRepository.findOne(friendId);
        if(first==null){
            throw new RuntimeException("User id not found- cannot find friend");
        }
        if(distance==0){
            List<Users> temp=new ArrayList<>();
            temp.add(friendRepository.findOne(friendId));
            return temp;
        }
        HashMap<Integer,Boolean> keyVisitedList=new HashMap<Integer,Boolean>();
        keyVisitedList.put(friendId,false);
        int temp=0;
        HashMap<Integer,List<Integer>> idProvidedFriendsList=new HashMap<Integer,List<Integer>>();
        //test
        HashMap<Integer,List<Integer>> distanceFriendList=new HashMap<Integer,List<Integer>>();
        List<Integer> distanceFriendUserList=new ArrayList<>();

        for(int i=0;i<distance;i++){
               for(Map.Entry<Integer,Boolean> entry:keyVisitedList.entrySet()) {
                   if (entry.getValue() == false) {
                       List<Integer> friendList = findUsersAllFriendsId(entry.getKey());
                        if(friendList!=null && !friendList.isEmpty()) {
                            //to remove any incomming same friends which are at already previous level
                            for (int k = 0; k < friendList.size(); k++) {
                                if (keyVisitedList.containsKey(friendList.get(k))) {
                                    friendList.remove(k);
                                    k--;
                                }
                            }
                            if (i == distance - 1) {
                                distanceFriendList.put(entry.getKey(), friendList);
                                distanceFriendUserList.addAll(friendList);
                            }

                            idProvidedFriendsList.put(entry.getKey(), friendList);
                        }
                       keyVisitedList.replace(entry.getKey(), true);
                       if (!keyVisitedList.containsValue(false)) {
                           break;
                       }
                   }
               }
            for(Map.Entry<Integer,List<Integer>> entry:idProvidedFriendsList.entrySet()) {
                for(Integer id:entry.getValue()){
                    if(!keyVisitedList.containsKey(id)) {
                        keyVisitedList.put(id, false);
                    }
                }
               }
        }
        Collections.sort(distanceFriendUserList);
        return friendRepository.findMultipleFriends(distanceFriendUserList);
    }


    public List<Integer> findUsersAllFriendsId(int friendId) {
        Users first=friendRepository.findOne(friendId);
        if(first!=null){
            //   "select friendOne,friendTwo from friends where friendOne=? or friendTwo=?";
            List<Friends> friendsList= friendRepository.findUsersAllFriends(friendId);
            List<Integer> friendIdList=new ArrayList<>();
            for(Friends friends:friendsList ){
                if(friends.getFriendOne()==friendId){
                    friendIdList.add (friends.getFriendTwo());
                }else if(friends.getFriendTwo()==friendId){
                    friendIdList.add (friends.getFriendOne());
                }
            }
            return friendIdList;
        }else{
            throw new RuntimeException("User id not found- cannot find friend");
        }
    }
}
