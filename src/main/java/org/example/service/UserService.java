package org.example.service;

import org.example.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 사용자 목록을 조회한다
     * @return List<User>
     */
    public List<User> getAllUsers() throws Exception;

    /**
     * 사용자의 상세 정보를 조회한다
     * @param id
     * @return User
     */
    public User getUserDetail(Long id) throws Exception;

    /**
     * 새로운 사용자를 추가한다
     * @param user
     * @return
     */
    public User addUser(User user) throws Exception;

    /**
     * 사용자의 정보를 업데이트 한다
     * @param user
     * @return User
     */
    public User updateUser(User user) throws Exception;

    /**
     * 사용자를 삭제한다
     * @param id
     * @return String
     */
    public String deleteUser(Long id) throws Exception;
}
