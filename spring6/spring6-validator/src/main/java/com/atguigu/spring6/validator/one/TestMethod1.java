package com.atguigu.spring6.validator.one;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
public class TestMethod1 {
    public static void main(String[] args) {
        //创建person对象
        Person person = new Person();
        person.setName("lucy");
        person.setAge(-1);

        // 创建Person对应的DataBinder
        DataBinder binder = new DataBinder(person);

        // 设置校验
        binder.setValidator(new PersonValidator());

        // 由于Person对象中的属性为空，所以校验不通过
        binder.validate();

        //输出结果
        BindingResult results = binder.getBindingResult();
        System.out.println(results.getAllErrors());
        //程序输出：
        //[Field error in object 'target' on field 'age': rejected value [-1];
        // codes [age.value.error.target.age,age.value.error.age,age.value.error.int,age.value.error];
        // arguments []; default message [age < 0]]
    }
}