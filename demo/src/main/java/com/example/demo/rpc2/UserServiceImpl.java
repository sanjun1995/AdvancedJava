package com.example.demo.rpc2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caozhixin
 * @date 2023/3/31 21:47
 */
public class UserServiceImpl implements UserService, Service {
    private List<User> userList;
    public UserServiceImpl() {
        userList = new ArrayList<>();
        userList.add(new User(1, "Alice"));
        userList.add(new User(2, "Bob"));
        userList.add(new User(3, "Charlie"));
    }
    @Override
    public User getUser(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public Response callMethod(String methodName, String[] args) {
        switch (methodName) {
            case "getUser": {
                int id = Integer.parseInt(args[0]);
                User user = getUser(id);
                if (user == null) {
                    return new Response("No such user: " + id);
                } else {
                    return new Response(user.toString());
                }
            }
            case "getAllUsers": {
                List<User> userList = getAllUsers();
                StringBuilder sb = new StringBuilder();
                for (User user : userList) {
                    sb.append(user.toString()).append("\n");
                }
                return new Response(sb.toString());
            }
            default:
                return new Response("No such method: " + methodName);
        }
    }
}