package org.example.service;

import org.example.domain.User;

import java.util.List;

public interface UserDao {

    /**
     * 사용자 목록을 조회한다
     * @return List<User>
     */
    public List<User> getAllUsers();

    /**
     * 사용자의 상세 정보를 조회한다
     * @param id
     * @return
     */
    public User getUserDetail(Long id);

    /**
     * 새로운 사용자를 추가한다
     * @param user
     * @return
     */
    public User addUser(User user);

    /**
     * 사용자의 정보를 업데이트 한다
     * @param user
     * @return User
     */
    public User updateUser(User user);

    /**
     * 사용자를 삭제한다
     * @param id
     * @return
     */
    public void deleteUser(Long id);
}
