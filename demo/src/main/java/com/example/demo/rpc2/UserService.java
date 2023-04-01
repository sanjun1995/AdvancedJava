package com.example.demo.rpc2;

import java.util.List;

/**
 * @author caozhixin
 * @date 2023/3/31 21:44
 */
public interface UserService {
    User getUser(int id);
    List<User> getAllUsers();
}
