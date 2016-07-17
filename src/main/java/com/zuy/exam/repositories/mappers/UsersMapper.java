package com.zuy.exam.repositories.mappers;

import com.zuy.exam.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {

    List<User> selectUsers();

    User selectUserById(int id);

    User selectUserByLogin(String login);

    void insertUser(User user);

    void updateUserLoginAndPasswordHash(User user);

    void deleteUsers(@Param("user_ids") int[] ids);

    boolean selectExistsAnotherUserWithRole(@Param("except_ids") int[] exceptIds, @Param("role") String role);
}
