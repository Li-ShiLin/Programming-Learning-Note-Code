package com.itheima.framework.beans.factory.support;

import com.itheima.framework.beans.BeanDefinition;
import java.util.HashMap;
import java.util.Map;
/**
 * @Description: 注册表接口的子实现类
 */
public class SimpleBeanDefinitionRegistry implements BeanDefinitionRegistry {

    //定义一个容器，用来存储BeanDefinition对象
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    public void removeBeanDefinition(String beanName) throws Exception {
        beanDefinitionMap.remove(beanName);
    }

    public BeanDefinition getBeanDefinition(String beanName) throws Exception {
        return beanDefinitionMap.get(beanName);
    }

    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }

    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
