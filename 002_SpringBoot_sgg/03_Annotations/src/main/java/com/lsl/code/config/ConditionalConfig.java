package com.lsl.code.config;
import com.lsl.code.bean.Owner;
import com.lsl.code.bean.Pet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
//@ConditionalOnBean(name = "TomCat")
@ConditionalOnMissingBean(name = "TomCat")
public class ConditionalConfig {

    @Bean
    public Owner owner(){
        Owner owner = new Owner("����ţ��",30);
        // owner���������petTomCat���
        owner.setPet(petTomCat());
        return owner;
    }

//    @Bean("TomCat")
    public Pet petTomCat(){
        return new Pet("TomCat",2);
    }
}
