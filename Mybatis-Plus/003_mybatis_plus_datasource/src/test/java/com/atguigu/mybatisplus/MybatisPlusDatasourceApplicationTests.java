package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.service.ProductService;
import com.atguigu.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusDatasourceApplicationTests {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	@Test
	public void test(){
		//  SELECT uid,user_name,age,sex,email,is_deleted FROM t_user WHERE uid=?
		System.out.println(userService.getById(1));
		// SELECT id,name,price,version FROM product WHERE id=?
		System.out.println(productService.getById(1));
	}

}
