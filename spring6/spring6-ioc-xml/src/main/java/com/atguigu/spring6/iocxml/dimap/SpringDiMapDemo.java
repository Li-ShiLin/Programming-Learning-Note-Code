package com.atguigu.spring6.iocxml.dimap;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDiMapDemo {

    // 为Map集合类型属性赋值
    @Test
    public void testMapDi() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-diref.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println("学生编号： " + student.getSid() + "学生名称：" + student.getSname());
        System.out.println(student.getTeacherMap());
        //程序输出：
        // 学生编号： 10000学生名称：lucy
        // {10010=Teacher{teacherId='100', teacherName='西门讲师'}, 10086=Teacher{teacherId='200', teacherName='欧阳讲师'}}
    }

    // 为引用集合类型的属性赋值
    @Test
    public void testRefDi() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-diref.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println("学生编号： " + student.getSid() + "学生名称：" + student.getSname());
        System.out.println(student.getLessonList());
        System.out.println(student.getTeacherMap());
        //程序输出：
        // 学生编号： 10000学生名称：lucy
        //        [Lesson{lessonName='java开发'}, Lesson{lessonName='前端开发'}]
        // {10010=Teacher{teacherId='100', teacherName='西门讲师'}, 10086=Teacher{teacherId='200', teacherName='欧阳讲师'}}
    }

    // P命名空间
    @Test
    public void testDiPNamespace() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-diref.xml");
        Student student = context.getBean("studentp", Student.class);
        System.out.println("学生编号： " + student.getSid() + "学生名称：" + student.getSname());
        System.out.println(student.getLessonList());
        System.out.println(student.getTeacherMap());
        //程序输出：
        // 学生编号： 100学生名称：mary
        //        [Lesson{lessonName='java开发'}, Lesson{lessonName='前端开发'}]
        // {10010=Teacher{teacherId='100', teacherName='西门讲师'}, 10086=Teacher{teacherId='200', teacherName='欧阳讲师'}}
    }
}
