package com.zuy.exam.repositories.mappers;

import com.zuy.exam.entities.User;

import java.util.List;

public interface UsersMapper {

    List<User> selectUsers();

    User selectUserById(int id);

    User selectUserByLogin(String login);

    void insertUser(User user);
}
