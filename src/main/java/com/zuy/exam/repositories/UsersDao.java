package com.zuy.exam.repositories;

import com.zuy.exam.repositories.mappers.UsersMapper;
import com.zuy.exam.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDao {

    @Autowired
    private UsersMapper usersMapper;

    public User getUser(int id) {
        return usersMapper.selectUserById(id);
    }
}
