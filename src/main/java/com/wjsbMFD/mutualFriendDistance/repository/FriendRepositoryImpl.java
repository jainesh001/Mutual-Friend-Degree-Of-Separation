package com.wjsbMFD.mutualFriendDistance.repository;

import com.wjsbMFD.mutualFriendDistance.entity.Friends;
import com.wjsbMFD.mutualFriendDistance.entity.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FriendRepositoryImpl implements FriendRepository{

    private EntityManager entityManager;

    @Autowired
    public FriendRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Users> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query theQuery = currentSession.createNativeQuery("select * from users");

        // execute query and get result list
        List<Users> users = theQuery.getResultList();

        // return the results
        return users;
    }

    @Override
    @Transactional
    public List<Friends> findAllFriends() {
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Friends> theQuery = currentSession.createNativeQuery("Select friend_id,friendOne,friendTwo from friends");

        // execute query and get result list
        List<Friends> friends = theQuery.getResultList();

        // return the results
        return friends;
    }

    @Override
    @Transactional
    public Users findOne(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Users users = currentSession.get(Users.class,id);
        return users;
    }

    @Override
    @Transactional
    public void insertUser(Users user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.createNativeQuery("insert into users (first_name,last_name,email,password) values(?,?,?,?)")
                .setParameter(1,user.getFirstName())
                .setParameter(2,user.getLastName())
                .setParameter(3,user.getEmail())
                .setParameter(4,user.getPassword()).executeUpdate();

    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query q=currentSession.createQuery("delete from users where id=:userId");
        q.setParameter("userId",id);
        q.executeUpdate();
    }

    @Override
    @Transactional
    public int lookForAlreadyPresentFriend(int friendIdOne, int friendIdTwo) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query q=currentSession.createNativeQuery("select friend_id from friends where ((friendOne=?  && friendTwo=?) ||(friendOne=?  && friendTwo=?)) LIMIT 1")
                .setParameter(1,friendIdOne)
                .setParameter(2,friendIdTwo)
                .setParameter(3,friendIdTwo)
                .setParameter(4,friendIdOne);
        List<Integer> list=q.getResultList();
        if(list!=null && !list.isEmpty()) {
            for (int i : list) {
                return i;
            }
        }
        return 0;
    }

    @Override
    @Transactional
    public List<Integer> lookForAlreadyPresentFriends(int friendIdOne, int friendIdTwo) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query q=currentSession.createNativeQuery("select friend_id from friends where ((friendOne=?  && friendTwo=?) ||(friendOne=?  && friendTwo=?))")
                .setParameter(1,friendIdOne)
                .setParameter(2,friendIdTwo)
                .setParameter(3,friendIdTwo)
                .setParameter(4,friendIdOne);
        List<Integer> list=q.getResultList();
        return list;
    }

    @Override
    @Transactional
    public void insertFriend(int friendIdOne, int friendIdTwo) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.createNativeQuery("insert into friends (friendOne,friendTwo) values(?,?)")
                .setParameter(1,friendIdOne)
                .setParameter(2,friendIdTwo).executeUpdate();
    }

    @Override
    @Transactional
    public void deleteFriend(int friendUId) {
            Session currentSession = entityManager.unwrap(Session.class);
            Query q=currentSession.createNativeQuery("delete from friends where friend_id=:friendUId");
            q.setParameter("friendUId",friendUId);
            q.executeUpdate();
    }

    @Override
    public List<Friends> findUsersAllFriends(int friendId) {
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Object[]> theQuery = currentSession.createNativeQuery("select friend_id,friendOne,friendTwo,date_Created from friends where friendOne=? or friendTwo=?")
                .setParameter(1,friendId)
                .setParameter(2,friendId);

        // execute query and get result list
        List<Object[]> o = (List<Object[]>) theQuery.getResultList();
        List<Friends> friends=new ArrayList<>();

        for (int j = 0; j < o.size(); j++) {
            Friends friends1=new Friends();
            friends1.setFriendOne((Integer)o.get(j)[1]);
            friends1.setFriendTwo((Integer)o.get(j)[2]);
            friends.add(friends1);
        }

        return friends;
    }

    @Override
    public List<Users> findMultipleFriends(List<Integer> distanceFriendUserList) {

        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
//        Query query=currentSession.createQuery("from users where user_id in (:id)");
//        query.setParameter("id",distanceFriendUserList);
//        List<Users> resultList = query.getResultList();
        Query query=currentSession.createNativeQuery("select * from users where user_id in (:id)");
        query.setParameter("id",distanceFriendUserList);
        List<Users> resultList = query.getResultList();

        return resultList;
        }
    }



