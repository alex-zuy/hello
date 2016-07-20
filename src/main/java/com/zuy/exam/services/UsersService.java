package com.zuy.exam.services;

import com.zuy.exam.entities.User;
import com.zuy.exam.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UsersService {

    private static final String DEFAULT_USER_ROLE = "ROLE_USER";

    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    private static final String AT_LEAST_ONE_ADMIN_SHOULD_EXIST =
        "users.service.at.least.one.admin.should.exist";

    private static final String USER_LOGIN_ALREADY_EXISTS =
        "users.service.user.login.already.exists";

    private static final String LOGIN_FIELD = "login";

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MessageSource messageSource;

    public List<User> getUsers() {
        return usersRepository.getUsers();
    }

    public User getUser(int id) {
        return usersRepository.getUser(id);
    }

    public void createUser(User user) {
        try {
            user.setRole(DEFAULT_USER_ROLE);
            usersRepository.createUser(user);
        }
        catch (DuplicateKeyException e) {
            throw new UsersServiceException(
                messageSource.getMessage(USER_LOGIN_ALREADY_EXISTS, null, Locale.getDefault()),
                LOGIN_FIELD);
        }
    }

    public void updateUserCredentials(User user) {
        try {
            usersRepository.updateUserLoginAndPassword(user);
        }
        catch (DuplicateKeyException e) {
            throw new UsersServiceException(
                messageSource.getMessage(USER_LOGIN_ALREADY_EXISTS, null, Locale.getDefault()),
                LOGIN_FIELD);
        }
    }

    public void deleteUsers(int[] ids) {
        if (usersRepository.existsAnotherUserWithRole(ids, ROLE_ADMIN)) {
            usersRepository.deleteUsers(ids);
        }
        else {
            throw new UsersServiceException(
                messageSource.getMessage(
                    AT_LEAST_ONE_ADMIN_SHOULD_EXIST, null, Locale.getDefault()),
                null);
        }
    }

    public static class UsersServiceException extends RuntimeException {

        private final String fieldName;

        public UsersServiceException(final String message, final String fieldName) {
            super(message);
            this.fieldName = fieldName;
        }

        public UsersServiceException(final String message, final Throwable throwable,
            final String fieldName) {

            super(message, throwable);
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }
    }
}
