package org.example.service;

import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() throws Exception {
        List<User> result = userDao.getAllUsers();
        return result;
    };

    public User getUserDetail(Long id) throws Exception {
        User user = userDao.getUserDetail(id);
        return user;
    };

    public User updateUser(User user) throws Exception {
        if(user.getId() != null) {
            userDao.updateUser(user);
        }
        return user;
    }

    public String deleteUser(Long id) throws Exception {
        userDao.deleteUser(id);
        return "SUCCESS";
    };
}
