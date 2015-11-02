package com.bestmarket.service;

import com.bestmarket.entity.*;
import org.dbunit.Assertion;
import org.dbunit.database.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/config/application-context.xml",
        "classpath:/config/spring-data.xml"
})
public class TestUserService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    private IDatabaseConnection connection;
    private User user;

    @Before
    public void before() throws Exception{
        IDataSet beforeDataSet = new FlatXmlDataSetBuilder().build(new File(
                "./src/test/resources/model/dataset.xml"
        ));
        connection = new DatabaseDataSourceConnection(dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(connection, beforeDataSet);

        user = new User("firstname", "lastname", "some@mail.com", "(099) 999-99-99", "123456", Role.USER);
    }

    @After
    public void after() {
        user = null;
    }

    @Test
    public void test_save_user() throws Exception {
        userService.saveAnfFlush(user);

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(
                "./src/test/resources/model/save-user.xml"
        ));
        ITable expected = expectedDataSet.getTable("users");

        ITable actual = connection.createQueryTable("users",
                "SELECT first_name, last_name, email, phone, password, role FROM users");

        Assertion.assertEquals(expected, actual);
    }

    @Test
    public void test_find_user_by_email() throws Exception {
        String email = "admin@gmail.com";

        User user = userService.findByMail(email);

        Assert.assertEquals(email, user.getEmail());
    }
}
