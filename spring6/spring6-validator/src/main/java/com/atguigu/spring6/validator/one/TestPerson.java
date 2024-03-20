package com.atguigu.spring6.validator.one;

import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

//校验测试
public class TestPerson {

    public static void main(String[] args) {
        //创建person对象
        Person person = new Person();
        person.setName("lucy");
        person.setAge(250);

        //创建person对应databinder
        DataBinder binder = new DataBinder(person);

        //设置校验器
        binder.setValidator(new PersonValidator());

        //调用方法执行校验
        binder.validate();

        //输出校验结果
        BindingResult result = binder.getBindingResult();
        System.out.println("error: " + result.getAllErrors());
        //程序输出：
        //error: [Field error in object 'target' on field 'age': rejected value [250];
        // codes [age.value.error.old.target.age,age.value.error.old.age,age.value.error.old.int,age.value.error.old];
        // arguments []; default message [age > 200]]
    }
}
