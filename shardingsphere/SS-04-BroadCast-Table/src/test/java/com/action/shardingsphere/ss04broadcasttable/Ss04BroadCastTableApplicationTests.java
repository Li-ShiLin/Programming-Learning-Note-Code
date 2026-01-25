package com.action.shardingsphere.ss04broadcasttable;

import com.action.shardingsphere.ss04broadcasttable.entity.Udict;
import com.action.shardingsphere.ss04broadcasttable.mapper.UdictMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ss04BroadCastTableApplicationTests {

    //注入udict 的mapper
    @Autowired
    private UdictMapper udictMapper;

    // 检查 Java 版本（用于验证运行时使用的 JDK 版本）
    static {
        String javaVersion = System.getProperty("java.version");
        String javaHome = System.getProperty("java.home");
        System.out.println("========================================");
        System.out.println("当前运行 Java 版本: " + javaVersion);
        System.out.println("当前 Java 路径: " + javaHome);
        System.out.println("========================================");
    }

    //======================测试公共表===================
    //添加操作
    @Test
    public void addDict() {
        Udict udict = new Udict();
        udict.setUstatus("a");
        udict.setUvalue("已启用");
        udictMapper.insert(udict);
        System.out.println("插入成功，生成的dictid: " + udict.getDictid());
    }

    //删除操作
    @Test
    public void deleteDict() {
        QueryWrapper<Udict> wrapper = new QueryWrapper<>();
        //设置dictid 值
        wrapper.eq("dictid", 1217975870505877505L);
        int result = udictMapper.delete(wrapper);
        System.out.println("删除结果: " + (result > 0 ? "成功" : "失败"));
    }

    //查询操作 - 查询所有字典
    @Test
    public void findAllDicts() {
        java.util.List<Udict> dicts = udictMapper.selectList(null);
        System.out.println("查询所有字典，数量: " + dicts.size());
        for (Udict dict : dicts) {
            System.out.println("  dictid=" + dict.getDictid() + ", ustatus=" + dict.getUstatus() + ", uvalue=" + dict.getUvalue());
        }
    }

    //查询操作 - 根据dictid查询
    @Test
    public void findDictById() {
        Udict dict = udictMapper.selectById(465191484111454209L);
        System.out.println("根据dictid查询结果: " + dict);
    }
}
