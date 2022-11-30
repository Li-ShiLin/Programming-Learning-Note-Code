##  1.ע��ʹ�ã�@Configuration ��@Import

####  1.1  @Configuration ��@Import��ʾ

**springBoot����Bean**

User��

```java
package com.lsl.code.bean;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

```

Cat��

```java
package com.lsl.code.bean;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class Cat {
    private String name;

    public Cat(String name) {
        this.name = name;
    }
}
```

BeanConfig������

```java
package com.lsl.code.config;

import com.lsl.code.bean.User;
import com.lsl.code.springbean.bean.Owner;
import com.lsl.code.bean.Cat;
import org.apache.logging.slf4j.MDCContextMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*@Configuration
        ����SpringBoot����һ��������
        ����������õ�ͬ�������ļ�
        �����ࣨ�˴���BeanConfig������Ҳ�����*/

/*     ���@Configuration(proxyBeanMethods = true)�����������÷�����
       SpringBoot�ܻ����������Ƿ����������� ����>  ���������ʵ��*/


/*proxyBeanMethods������bean�ķ���
       Fullģʽ��proxyBeanMethods = true ,��֤ÿ��@Bean���������ö��ٴη��ص�������ǵ�ʵ����
               Fullģʽ�ŵ㣺��֤��ʵ��
               �����������������������Ҫʹ�������������Fullģʽ����proxyBeanMethods ����Ϊ true
       Liteģʽ��proxyBeanMethods = false ,ÿ��@Bean���������ö��ٴη��ص���������´�����
               Liteģʽ�ŵ㣺springboot�����ж�@Beanע��ķ������ص�������������Ƿ���ڣ�����������
               ��������������е�����������Liteģʽ,��proxyBeanMethods ����Ϊ false*/

@Configuration(proxyBeanMethods = true)
@Import({Owner.class, MDCContextMap.class})
/* @Import({Owner.class, MDCContextMap.class})
 *      ���������Զ����������������͵������Ĭ����������־���ȫ����
 */
public class BeanConfig {
    /*@Bean��������������:
            �Է�������Ϊ�����id
            �������;����������
            ���ص�ֵ����������������е�ʵ��
    
            ����������ʹ��@Bean��ע�ڷ����ϸ�����ע������Ĭ���ǵ�ʵ����
            �ⲿ���۶��������е�������ע�᷽�����ö��ٴλ�ȡ�Ķ���֮ǰע�������еĵ�ʵ������
            proxyBeanMethods :����bean�ķ���*/
    @Bean
    public User user() {
        return new User("zhangsan", 18);
    }

    @Bean
    public Cat cat() {
        return new Cat("��è����");
    }
}
```

��ȡBean��

```java
package com.lsl.code;

import com.lsl.code.bean.User;
import com.lsl.code.springbean.bean.Owner;
import com.lsl.code.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 1.����IOC����
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);

        // 2.�鿴������������
        String[] beanNames = run.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        // 3.�������л�ȡ����
        User user01 = run.getBean("user", User.class);
        User user02 = run.getBean("user", User.class);
        System.out.println(user01 == user02); //true -> ע������Ĭ���ǵ�ʵ����
    }

}
/*����̨�����
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
application
org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory
beanConfig
com.lsl.code.springbean.bean.Owner
org.apache.logging.slf4j.MDCContextMap
user
cat
org.springframework.boot.autoconfigure.AutoConfigurationPackages
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration
propertySourcesPlaceholderConfigurer
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration
mbeanExporter
objectNamingStrategy
mbeanServer
org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor
org.springframework.boot.context.internalConfigurationPropertiesBinderFactory
org.springframework.boot.context.internalConfigurationPropertiesBinder
org.springframework.boot.context.properties.BoundConfigurationProperties
org.springframework.boot.context.properties.EnableConfigurationPropertiesRegistrar.methodValidationExcludeFilter
spring.jmx-org.springframework.boot.autoconfigure.jmx.JmxProperties
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration
springApplicationAdminRegistrar
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration$ClassProxyingConfiguration
forceAutoProxyCreatorToUseClassProxying
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration
applicationAvailability
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration
org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration
lifecycleProcessor
spring.lifecycle-org.springframework.boot.autoconfigure.context.LifecycleProperties
org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration
spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties
org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration
spring.sql.init-org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties
org.springframework.boot.sql.init.dependency.DatabaseInitializationDependencyConfigurer$DependsOnDatabaseInitializationPostProcessor
org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration
taskExecutorBuilder
applicationTaskExecutor
spring.task.execution-org.springframework.boot.autoconfigure.task.TaskExecutionProperties
org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration
scheduledBeanLazyInitializationExcludeFilter
taskSchedulerBuilder
spring.task.scheduling-org.springframework.boot.autoconfigure.task.TaskSchedulingProperties
org.springframework.aop.config.internalAutoProxyCreator
true*/
```

####   1.2�ܽ�

**@Configuration**

- ��@Configurationע������ʾ�������࣬ ����������õ�ͬ�������ļ��������౾��Ҳ�����

  - Fullģʽ��proxyBeanMethods = true ,��֤ÿ��@Bean���������ö��ٴη��ص�������ǵ�ʵ����

  - Fullģʽ�ŵ㣺��֤��ʵ��

-  Liteģʽ��proxyBeanMethods = false ,ÿ��@Bean���������ö��ٴη��ص���������´�����

  - Liteģʽ�ŵ㣺springboot�����ж�@Beanע��ķ������ص�������������Ƿ���ڣ�����������

- Liteģʽ&Fullģʽ���ʵս

  - ���������֮����������ϵ��Liteģʽ���������������̣������ж�

  - ���������֮����������ϵ�������ᱻ���õõ�֮ǰ��ʵ���������Fullģʽ

**@Import**

- �������е��������Ĭ����������־���ȫ����
- @Importע��߼��÷���https://www.bilibili.com/video/BV1gW411W7wy?p=8

**���⣬����ע��Ҳ���Դ���Bean��ע������**

  @Bean��@Component��@Controller��@Service��@Repository