package com.zuy.exam.repositories;

import com.zuy.exam.repositories.mappers.UsersMapper;
import com.zuy.exam.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersRepository {

    @Autowired
    private UsersMapper usersMapper;

    public List<User> getUsers() {
        return usersMapper.selectUsers();
    }

    public User getUser(int id) {
        return usersMapper.selectUserById(id);
    }

    public User getUser(String login) {
        return usersMapper.selectUserByLogin(login);
    }

    public void createUser(User user) {
        usersMapper.insertUser(user);
    }

    public void updateUserLoginAndPassword(User user) {
        usersMapper.updateUserLoginAndPasswordHash(user);
    }

    public void deleteUsers(int[] ids) {
        usersMapper.deleteUsers(ids);
    }

    public boolean existsAnotherUserWithRole(int[] exceptIds, String role) {
        return usersMapper.selectExistsAnotherUserWithRole(exceptIds, role);
    }
}
