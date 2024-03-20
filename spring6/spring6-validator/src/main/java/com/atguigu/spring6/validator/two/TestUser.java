package com.atguigu.spring6.validator.two;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class TestUser {

    @Test
    public void testValidationOne() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ValidationConfig.class);
        MyValidation1 validation1 = context.getBean(MyValidation1.class);

        User user = new User();
        user.setName("lucy");
        user.setAge(20);
        boolean message = validation1.validatorByUserOne(user);
        System.out.println(message);
        // true
    }

    @Test
    public void testValidationTwo() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ValidationConfig.class);
        MyValidation2 validation2 = context.getBean(MyValidation2.class);

        User user = new User();
        user.setName("lucy");
        user.setAge(200);

        boolean message = validation2.validatorByUserTwo(user);
        System.out.println(message);
        //程序输出：
        //[Field error in object 'lucy' on field 'age': rejected value [200]; codes [Max.lucy.age,Max.age,Max.int,Max]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [lucy.age,age]; arguments []; default message [age],150]; default message [最大不能超过150]]
        //true

    }
}
