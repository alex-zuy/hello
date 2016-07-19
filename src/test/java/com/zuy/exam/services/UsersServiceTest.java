package com.zuy.exam.services;

import com.zuy.exam.TestUtils;
import com.zuy.exam.config.MessagesConfig;
import com.zuy.exam.config.MybatisConfig;
import com.zuy.exam.config.TestDatabaseConfig;
import com.zuy.exam.entities.User;
import com.zuy.exam.repositories.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    MybatisConfig.class,
    MessagesConfig.class,
    UsersRepository.class,
    UsersService.class,
    TestDatabaseConfig.class})
@ActiveProfiles("test")
@Sql({"/schema.sql", "/insert_admin.sql"})
public class UsersServiceTest {

    private static final String USERS_TABLE = "users";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UsersService service;

    @Test
    public void testCreateUser() throws Exception {
        final User user = TestUtils.createUser("user", "password", null);
        service.createUser(user);
        assertNotNull(user.getId());
        assertNotNull(service.getUser(user.getId()));
    }

    @Test(expected = UsersService.UsersServiceException.class)
    public void testCreatingDuplicateLoginUser() throws Exception {
        final User user = TestUtils.createUser("admin", "passwordHash", null);
        service.createUser(user);
    }

    @Test
    public void testDeleteUser() throws Exception {
        final User user = TestUtils.createUser("user", "password", null);
        final int beforeInsert = JdbcTestUtils.countRowsInTable(jdbcTemplate, USERS_TABLE);
        service.createUser(user);
        final int afterInsert = JdbcTestUtils.countRowsInTable(jdbcTemplate, USERS_TABLE);
        service.deleteUsers(new int[]{user.getId()});
        final int afterDelete = JdbcTestUtils.countRowsInTable(jdbcTemplate, USERS_TABLE);

        assertEquals(beforeInsert + 1, afterInsert);
        assertEquals(beforeInsert, afterDelete);
    }
    
    @Test(expected = UsersService.UsersServiceException.class)
    public void testDeleteLastAdminUser() throws Exception {
        final User user = TestUtils.createUser("user", "password", null);
        service.createUser(user);

        final int[] adminUsersIds = jdbcTemplate.queryForList(
            "SELECT id FROM users WHERE role = 'ROLE_ADMIN'", Integer.class).stream()
            .mapToInt(Integer::intValue).toArray();

        service.deleteUsers(adminUsersIds);
    }
}
