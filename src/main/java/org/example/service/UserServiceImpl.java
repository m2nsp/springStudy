package org.example.service;

import org.example.domain.User;
import org.example.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (Exception e) {
            throw new UserServiceException("USER_LIST_RETRIEVE_FAILED", e);
        }
    };

    @Override
    public User getUserDetail(Long id) throws Exception {
        try {
            User user = userDao.getUserDetail(id);
            if(user == null) {
                throw new UserServiceException("USER_NOT_FOUND");
            }
            return user;
        } catch (Exception e) {
            throw new UserServiceException("USER_DETAIL_RETRIEVE_FAILED", e);
        }
    };

    @Override
    public User addUser(User user) throws Exception {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new Exception("NAME_MUST_NOT_BE_NULL");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new Exception("EMAIL_MUST_NOT_BE_NULL");
        }

        try {
            userDao.addUser(user);
            return userDao.getUserDetail(user.getId());
        } catch (Exception e) {
            throw new UserServiceException("USER_ADD_FAILED", e);
        }
    }

    @Override
    public User updateUser(User user) throws Exception {
        if(user.getId() == null ) {
            throw new UserServiceException("ID_MUST_NOT_BE_NULL");
        }
        try {
            userDao.updateUser(user);
            return userDao.getUserDetail(user.getId());
        } catch (Exception e) {
            throw new UserServiceException("USER_UPDATE_FAILED", e);
        }
    }

    @Override
    public String deleteUser(Long id) throws Exception {
        try {
            User user = userDao.getUserDetail(id);
            if(user == null) {
                throw new Exception("USER_NOT_FOUND");
            }
            userDao.deleteUser(id);
            return "SUCCESS";
        }  catch (Exception e) {
//            System.out.println("사용자 삭제 중 에러 발생 :" + e.getMessage());
            throw new UserServiceException("USER_DELETE_FAILED", e);
        }
    }
}
