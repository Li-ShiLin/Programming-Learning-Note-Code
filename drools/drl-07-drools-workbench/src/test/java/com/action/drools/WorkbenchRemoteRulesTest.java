package com.action.drools;

import com.inaction.pro1.Person;
import org.drools.core.io.impl.UrlResource;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.InputStream;

/**
 * 对应教程 8.3.7：从 WorkBench 暴露的 Maven 仓库 URL 加载规则 jar 并执行。
 * <p>
 * 默认跳过（无需启动 Tomcat）。启用方式：
 * {@code mvn test -Dworkbench.remote.test=true}
 */
public class WorkbenchRemoteRulesTest {

    @Test
    public void test1() throws Exception {
        //通过此URL可以访问到maven仓库中的jar包
        //URL地址构成：http://ip地址:Tomcat端口号/WorkBench工程名/maven2/坐标/版本号/xxx.jar
        String url = "http://192.168.56.11:8080/kie-drools-wb/maven2/com/inaction/pro1/1.0.0/pro1-1.0.0.jar";

        KieServices kieServices = KieServices.Factory.get();

        //通过Resource资源对象加载jar包
        UrlResource resource = (UrlResource) kieServices.getResources().newUrlResource(url);
        //通过Workbench提供的服务来访问maven仓库中的jar包资源，需要先进行Workbench的认证
        resource.setUsername("kie");
        resource.setPassword("kie");
        resource.setBasicAuthentication("enabled");

        //将资源转换为输入流，通过此输入流可以读取jar包数据
        InputStream inputStream = resource.getInputStream();

        //创建仓库对象，仓库对象中保存Drools的规则信息
        KieRepository repository = kieServices.getRepository();

        //通过输入流读取maven仓库中的jar包数据，包装成KieModule模块添加到仓库中
        KieModule kieModule = repository.addKieModule(kieServices.getResources().newInputStreamResource(inputStream));

        //基于KieModule模块创建容器对象，从容器中可以获取session会话
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        KieSession session = kieContainer.newKieSession();

        Person person = new Person();
        person.setAge(6);
        session.insert(person);

        session.fireAllRules();
        session.dispose();
    }
}
