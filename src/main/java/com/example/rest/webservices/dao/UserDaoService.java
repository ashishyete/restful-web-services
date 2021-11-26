package com.example.rest.webservices.dao;

import com.example.rest.webservices.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 5;

    static {
        users.add(new User(1, "Ashish", new Date()));
        users.add(new User(2, "Nidhi", new Date()));
        users.add(new User(3, "Ayana", new Date()));
        users.add(new User(4, "Deepak", new Date()));
        users.add(new User(5, "Mangala", new Date()));

    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    /*public List<User> deleteUser(Integer id) {
        User user = findOne(id);
        if(user!=null)
            users.remove(user);
        return users;
    }*/
}
