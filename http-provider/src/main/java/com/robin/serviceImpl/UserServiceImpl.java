package com.robin.serviceImpl;

import com.robin.bean.User;
import com.robin.interf.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robin on 2016/10/8.
 */
@Component("userService")
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        System.out.println("---------------------");
    }

    static List<User> list = new ArrayList<>();

    static {
        list.add(new User(1, "robin01"));
        list.add(new User(2, "robin02"));
        list.add(new User(3, "robin03"));
    }

    @Override
    public User getUser(int id) {
        return list.get(id);
    }
}
