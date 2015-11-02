package com.bestmarket.service;

import com.bestmarket.entity.*;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.*;
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
public class TestProductService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ProductService productService;

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
    public void test_find_all_available() {
        List<Product> products = productService.findAllAvailable();

        for (Product product : products) {
            if (product.getCount() <= 0) {
                expectedFlag = false;
                break;
            }
        }

        Assert.assertEquals(expectedFlag, true);
    }

    @Test
    public void test_find_available_by_category() {
        Category categoryTablets = Category.TABLETS;

        List<Product> products = productService.findAvailableByCategory(categoryTablets);

        for (Product product : products) {
            if (product.getCount() <= 0 || !product.getCategory().equals(categoryTablets)) {
                expectedFlag = false;
                break;
            }
        }

        Assert.assertEquals(expectedFlag, true);
    }

    @Test
    public void test_find_by_name() {
        String productName = "Asus Portable 1TB";

        Product product = productService.findByName(productName);

        if (!productName.equals(product.getName())) {
            expectedFlag = false;
        }

        Assert.assertEquals(expectedFlag, true);
    }
}
