package com.bestmarket.service;

import com.bestmarket.entity.*;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.*;
import org.dbunit.database.*;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/config/application-context.xml",
        "classpath:/config/spring-data.xml"
})
public class TestShoppingCart {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCart shoppingCart;

    private IDatabaseConnection connection;
    private List<Product> products;
    private User user;
    private boolean expectedFlag;

    @Before
    public void before() throws Exception{
        IDataSet beforeDataSet = new FlatXmlDataSetBuilder().build(new File(
                "./src/test/resources/model/dataset.xml"
        ));
        connection = new DatabaseDataSourceConnection(dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(connection, beforeDataSet);

        products = productService.findAllAvailable();

        expectedFlag = true;

        user = new User("firstname", "lastname", "some@mail.com", "(099) 999-99-99", "123456", Role.USER);
        userService.saveAnfFlush(user);
        shoppingCart.setUser(user);
    }

    @After
    public void after() {
        products = null;
        shoppingCart.clear();
    }

    @Test
    public void test_add_product() {
        Product product1 = products.get(0);
        Product product2 = products.get(1);

        shoppingCart.add(product1);
        shoppingCart.add(product2);

        Map<Product, Integer> productsInCart = shoppingCart.getProducts();
        for (Map.Entry<Product, Integer> pair : productsInCart.entrySet()) {
            Product product = pair.getKey();
            if (!products.contains(product)) {
                expectedFlag = false;
                break;
            }
        }

        Assert.assertEquals(expectedFlag, true);

    }

    @Test
    public void test_product_count() {
        Product product = products.get(0);

        shoppingCart.add(product);
        shoppingCart.add(product);

        Map<Product, Integer> productsInCart = shoppingCart.getProducts();
        int count = productsInCart.get(product);

        Assert.assertEquals(count, 2);
    }

    @Test
    public void test_remove_product() {
        Product product = products.get(0);

        shoppingCart.add(product);
        shoppingCart.add(product);
        shoppingCart.remove(product);

        Map<Product, Integer> productsInCart = shoppingCart.getProducts();
        int count = productsInCart.get(product);

        Assert.assertEquals(count, 1);
    }

    @Test
    public void total_price() {
        Product product1 = products.get(0);
        Product product2 = products.get(1);

        shoppingCart.add(product1);
        shoppingCart.add(product1);
        shoppingCart.add(product2);
        shoppingCart.remove(product2);
        shoppingCart.add(product2);

        BigDecimal totalPrice = new BigDecimal(0);

        Map<Product, Integer> productsInCart = shoppingCart.getProducts();
        for (Map.Entry<Product, Integer> pair : productsInCart.entrySet()) {
            Product product = pair.getKey();
            int count = pair.getValue();
            BigDecimal price =product.getPrice().multiply(BigDecimal.valueOf(count));
            totalPrice = totalPrice.add(price);
        }

        Assert.assertEquals(totalPrice, BigDecimal.valueOf(1899.97));
    }

    @Test
    public void test_user_order() throws Exception{
        Product product1 = products.get(0);
        Product product2 = products.get(1);

        shoppingCart.add(product1);
        shoppingCart.add(product2);

        shoppingCart.order();

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(
                "./src/test/resources/model/user-order.xml"
        ));
        ITable expected = expectedDataSet.getTable("user_order");

        ITable actual = connection.createQueryTable("user_order",
                "SELECT total_price FROM user_order");

        Assertion.assertEquals(expected, actual);
    }
}
