package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.example.domain.User;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    @Resource(name="sqlSession")
    private SqlSession sqlSession;
    // UserMapper를 주입해서 사용할 수도 있지만 방식을 동일하게 하려함으로 sqlSession 주입으로 ..!

    /**
     * 사용자 목록을 조회한다
     * @return List<User>
     */
    @Override
    public List<User> getAllUsers() {
        return sqlSession.getMapper(UserMapper.class).getAllUsers();
    };

    /**
     * 사용자의 상세 정보를 조회한다
     * @param id
     * @return
     */
    @Override
    public User getUserDetail(Long id) {
        return sqlSession.getMapper(UserMapper.class).getUserById(id);
    };

    /**
     * 새로운 사용자를 추가한다
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) {
        sqlSession.getMapper(UserMapper.class).addUser(user);
        User newUser = getUserDetail(user.getId());
        return newUser;
    }

    /**
     * 사용자의 정보를 업데이트 한다
     * @param user
     * @return User
     */
    @Override
    public User updateUser(User user) {
        sqlSession.getMapper(UserMapper.class).updateUser(user);
        User updatedUser = getUserDetail(user.getId());
        return updatedUser;
    }

    /**
     * 사용자를 삭제한다
     * @param id
     */
    @Override
    public void deleteUser(Long id) {
        sqlSession.getMapper(UserMapper.class).deleteUser(id);
    }

//    public String deleteUser(Long id) {
//        try {
//            User user = sqlSession.getMapper(UserMapper.class).getUserById(id);
//            if(user!=null){
//                throw new Exception("USER_NOT_FOUND");
//            }
//            sqlSession.getMapper(UserMapper.class).deleteUser(id);
//            return "SUCCESS";
//        } catch (Exception e) {
//            System.out.println("사용자 삭제 중 에러 발생 :" + e.getMessage());
//            return "FAILURE";
//        }
//    };
}
