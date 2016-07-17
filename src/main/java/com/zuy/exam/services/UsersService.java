package com.zuy.exam.services;

import com.zuy.exam.entities.User;
import com.zuy.exam.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> getUsers() {
        return usersRepository.getUsers();
    }

    public User getUser(int id) {
        return usersRepository.getUser(id);
    }

    public void storeUser(User user) {
        usersRepository.storeUser(user);
    }
}
