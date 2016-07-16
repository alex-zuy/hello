package com.zuy.exam.repositories.mappers;

import com.zuy.exam.entities.User;

public interface UsersMapper {

    User selectUserById(int id);
}
