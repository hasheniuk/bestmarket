package com.bestmarket.service;

import com.bestmarket.entity.*;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/config/application-context.xml",
        "classpath:/config/spring-data.xml"
})
public class TestOrderService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    private IDatabaseConnection connection;
    private boolean expectedFlag;

    @Before
    public void before() throws Exception {
        IDataSet beforeDataSet = new FlatXmlDataSetBuilder().build(new File(
                "./src/test/resources/model/dataset.xml"
        ));
        connection = new DatabaseDataSourceConnection(dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(connection, beforeDataSet);

        expectedFlag = true;
    }

    @Test
    public void find_by_user() {
        User user = userService.findByMail("dou@gmail.com");
        List<Order> orders = orderService.findByUser(user);

        for(Order order : orders) {
            if (!user.equals(order.getUser())) {
                expectedFlag = false;
                break;
            }
        }

        Assert.assertEquals(expectedFlag, true);
    }
}
