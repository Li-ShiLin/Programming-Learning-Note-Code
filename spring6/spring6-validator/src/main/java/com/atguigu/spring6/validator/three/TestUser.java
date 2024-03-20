package com.atguigu.spring6.validator.three;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class TestUser {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ValidationConfig.class);
        MyService service = context.getBean(MyService.class);
        User user = new User();
        user.setName("lucy");
        user.setPhone("13566754321");
        user.setMessage("test a t guigu");
        service.testMethod(user);
//程序输出：
//        default message :不能包含空格
//        Exception in thread "main" jakarta.validation.ConstraintViolationException: testMethod.arg0.message: can not contains blank
//        at org.springframework.validation.beanvalidation.MethodValidationInterceptor.invoke(MethodValidationInterceptor.java:131)
//        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
//        at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:752)
//        at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:703)
//        at com.atguigu.spring6.validator.three.MyService$$SpringCGLIB$$0.testMethod(<generated>)
//        at com.atguigu.spring6.validator.three.TestUser.main(TestUser.java:16)

    }
}
