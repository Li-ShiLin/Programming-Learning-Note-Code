package com.atguigu.spring6.iocxml.jdbc;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
public class TestJdbc {

    // 通过new的方式创建DruidDataSource
    @Test
    public void demo1() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    // 将DruidDataSource交给spring管理，并从IoC容器中获取DruidDataSource实例
    @Test
    public void demo2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbc.xml");
        DruidDataSource dataSource = context.getBean(DruidDataSource.class);
        System.out.println(dataSource.getUrl());
        //输出：jdbc:mysql://192.168.56.10:3306/spring?serverTimezone=UTC
    }

    @Test
    public void testDataSource() throws SQLException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean-jdbc.xml");
        DataSource dataSource = ac.getBean(DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
