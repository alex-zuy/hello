package com.zuy.exam.repositories;

import com.zuy.exam.TestUtils;
import com.zuy.exam.config.TestDatabaseConfig;
import com.zuy.exam.config.MybatisConfig;
import com.zuy.exam.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MybatisConfig.class, UsersRepository.class, TestDatabaseConfig.class})
@Sql({"/schema.sql", "/insert_admin.sql"})
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository repository;

    @Test
    public void testSpring() throws Exception {
        final User user = repository.getUser(1);
        assertNotNull(user);
        assertEquals("admin", user.getLogin());
    }

    @Test
    public void testInsertUser() throws Exception {
        final User newUser = TestUtils.createUser("new", "passwordHash", "role");
        repository.createUser(newUser);
        assertNotNull(newUser.getId());

        final User user = repository.getUser(newUser.getId());

        assertEquals(newUser.getId(), user.getId());
        assertEquals(newUser.getLogin(), user.getLogin());
        assertEquals(newUser.getPasswordHash(), user.getPasswordHash());
        assertEquals(newUser.getRole(), user.getRole());
    }

    @Test
    public void testUpdateUser() throws Exception {
        final User oldUser = TestUtils.createUser("oldLogin", "oldPassword", "oldRole");
        repository.createUser(oldUser);

        final User newUser = TestUtils.createUser("newLogin", "newPassword", "newRole");
        newUser.setId(oldUser.getId());
        repository.updateUserLoginAndPassword(newUser);

        final User user = repository.getUser(newUser.getId());

        assertEquals(newUser.getLogin(), user.getLogin());
        assertEquals(newUser.getPasswordHash(), user.getPasswordHash());
    }

    @Test(expected = DuplicateKeyException.class)
    public void testUniqueLoginConstraint() throws Exception {
        final User user = TestUtils.createUser("login", "password", "role");

        repository.createUser(user);
        repository.createUser(user);
    }

    @Test
    public void testDeleteUser() throws Exception {
        final User user = TestUtils.createUser("login", "password", "role");

        repository.createUser(user);
        assertNotNull(user.getId());
        repository.deleteUsers(new int[]{user.getId()});
        assertNull(repository.getUser(user.getId()));
    }

}
