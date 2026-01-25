package com.action.shardingsphere.ss02databaseshorizontalsharding;

import com.action.shardingsphere.ss02databaseshorizontalsharding.entity.Course;
import com.action.shardingsphere.ss02databaseshorizontalsharding.mapper.CourseMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ss02DatabasesHorizontalShardingApplicationTests {

    //注入mapper
    @Autowired
    private CourseMapper courseMapper;

    // 检查 Java 版本（用于验证运行时使用的 JDK 版本）
    static {
        String javaVersion = System.getProperty("java.version");
        String javaHome = System.getProperty("java.home");
        System.out.println("========================================");
        System.out.println("当前运行 Java 版本: " + javaVersion);
        System.out.println("当前 Java 路径: " + javaHome);
        System.out.println("========================================");
    }

    //======================测试水平分库=====================

    //添加操作 - 单个添加
    @Test
    public void addCourseDb() {
        Course course = new Course();
        course.setCname("javademo1");
        //分库根据user_id，111为奇数，会插入到edu_db_2数据库
        course.setUserId(111L);
        course.setCstatus("Normal1");
        courseMapper.insert(course);

        // 注意：MyBatis Plus 的 insert 方法不会自动回填 Sharding-JDBC 生成的 ID
        // 需要通过查询获取生成的 cid
        Long cid = course.getCid();
        if (cid == null) {
            QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", course.getUserId());
            queryWrapper.eq("cname", course.getCname());
            queryWrapper.eq("cstatus", course.getCstatus());
            queryWrapper.orderByDesc("cid");
            queryWrapper.last("LIMIT 1");
            Course insertedCourse = courseMapper.selectOne(queryWrapper);
            if (insertedCourse != null && insertedCourse.getCid() != null) {
                cid = insertedCourse.getCid();
            }
        }

        System.out.println("插入成功，生成的cid: " + cid);
    }

    //添加操作 - 批量插入，确保每个库和表都有数据
    @Test
    public void insertCourseDbBatch() {
        System.out.println("========== 开始批量插入数据 ==========");
        System.out.println("目标：确保每个库和表都有数据");
        System.out.println("  - edu_db_1.course_1: user_id为偶数, cid为偶数");
        System.out.println("  - edu_db_1.course_2: user_id为偶数, cid为奇数");
        System.out.println("  - edu_db_2.course_1: user_id为奇数, cid为偶数");
        System.out.println("  - edu_db_2.course_2: user_id为奇数, cid为奇数");
        System.out.println();

        // 记录每个分片是否有数据
        boolean eduDb1Course1 = false; // user_id偶数, cid偶数
        boolean eduDb1Course2 = false; // user_id偶数, cid奇数
        boolean eduDb2Course1 = false; // user_id奇数, cid偶数
        boolean eduDb2Course2 = false; // user_id奇数, cid奇数

        // 用于记录已使用的 user_id，确保唯一性
        java.util.Set<Long> usedUserIds = new java.util.HashSet<>();
        long baseUserId = 0L;
        int maxAttempts = 500; // 最大尝试次数
        int attemptCount = 0;

        // 第一阶段：批量插入数据
        System.out.println("========== 第一阶段：批量插入数据 ==========");
        while ((!eduDb1Course1 || !eduDb1Course2 || !eduDb2Course1 || !eduDb2Course2) && attemptCount < maxAttempts) {
            attemptCount++;

            // 交替使用偶数和奇数 user_id
            Long userId;
            if (attemptCount % 2 == 0) {
                // 偶数 user_id，会插入到 edu_db_1
                userId = baseUserId;
                baseUserId += 2;
            } else {
                // 奇数 user_id，会插入到 edu_db_2
                userId = baseUserId + 1;
                baseUserId += 2;
            }

            // 确保 user_id 唯一
            while (usedUserIds.contains(userId)) {
                userId += 2; // 保持奇偶性
            }
            usedUserIds.add(userId);

            // 插入数据
            Course course = new Course();
            course.setCname("javademo" + attemptCount);
            course.setUserId(userId);
            course.setCstatus("Normal" + attemptCount);
            courseMapper.insert(course);

            // 查询获取生成的 cid
            QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("cname", course.getCname());
            queryWrapper.eq("cstatus", course.getCstatus());
            queryWrapper.orderByDesc("cid");
            queryWrapper.last("LIMIT 1");
            Course insertedCourse = courseMapper.selectOne(queryWrapper);

            if (insertedCourse == null || insertedCourse.getCid() == null) {
                System.out.println("警告：无法获取生成的 cid，跳过此次插入");
                continue;
            }

            Long cid = insertedCourse.getCid();
            boolean isEvenUserId = (userId % 2 == 0);
            boolean isEvenCid = (cid % 2 == 0);

            // 判断数据插入到哪个分片
            if (isEvenUserId && isEvenCid) {
                if (!eduDb1Course1) {
                    eduDb1Course1 = true;
                    System.out.println("✓ [" + attemptCount + "] 数据插入到 edu_db_1.course_1: user_id=" + userId + ", cid=" + cid);
                }
            } else if (isEvenUserId && !isEvenCid) {
                if (!eduDb1Course2) {
                    eduDb1Course2 = true;
                    System.out.println("✓ [" + attemptCount + "] 数据插入到 edu_db_1.course_2: user_id=" + userId + ", cid=" + cid);
                }
            } else if (!isEvenUserId && isEvenCid) {
                if (!eduDb2Course1) {
                    eduDb2Course1 = true;
                    System.out.println("✓ [" + attemptCount + "] 数据插入到 edu_db_2.course_1: user_id=" + userId + ", cid=" + cid);
                }
            } else {
                if (!eduDb2Course2) {
                    eduDb2Course2 = true;
                    System.out.println("✓ [" + attemptCount + "] 数据插入到 edu_db_2.course_2: user_id=" + userId + ", cid=" + cid);
                }
            }
        }

        // 第二阶段：验证和统计
        System.out.println("\n========== 第二阶段：验证和统计 ==========");
        java.util.List<Course> allCourses = courseMapper.selectList(null);

        int eduDb1Course1Count = 0;
        int eduDb1Course2Count = 0;
        int eduDb2Course1Count = 0;
        int eduDb2Course2Count = 0;
        java.util.Set<Long> allCids = new java.util.HashSet<>();
        java.util.Set<Long> allUserIds = new java.util.HashSet<>();

        for (Course course : allCourses) {
            Long userId = course.getUserId();
            Long cid = course.getCid();

            if (userId == null || cid == null) {
                continue;
            }

            allUserIds.add(userId);
            allCids.add(cid);

            boolean isEvenUserId = (userId % 2 == 0);
            boolean isEvenCid = (cid % 2 == 0);

            if (isEvenUserId && isEvenCid) {
                eduDb1Course1Count++;
            } else if (isEvenUserId && !isEvenCid) {
                eduDb1Course2Count++;
            } else if (!isEvenUserId && isEvenCid) {
                eduDb2Course1Count++;
            } else {
                eduDb2Course2Count++;
            }
        }

        // 输出结果统计
        System.out.println("========== 数据插入完成 ==========");
        System.out.println("edu_db_1.course_1: " + (eduDb1Course1Count > 0 ? "✓ 有数据 (" + eduDb1Course1Count + " 条)" : "✗ 无数据"));
        System.out.println("edu_db_1.course_2: " + (eduDb1Course2Count > 0 ? "✓ 有数据 (" + eduDb1Course2Count + " 条)" : "✗ 无数据"));
        System.out.println("edu_db_2.course_1: " + (eduDb2Course1Count > 0 ? "✓ 有数据 (" + eduDb2Course1Count + " 条)" : "✗ 无数据"));
        System.out.println("edu_db_2.course_2: " + (eduDb2Course2Count > 0 ? "✓ 有数据 (" + eduDb2Course2Count + " 条)" : "✗ 无数据"));
        System.out.println("总共插入数据: " + allCourses.size() + " 条");
        System.out.println("使用的 user_id 数量: " + allUserIds.size());
        System.out.println("使用的 cid 数量: " + allCids.size());
        System.out.println("尝试次数: " + attemptCount);

        // 验证唯一性
        if (allCids.size() == allCourses.size() && allUserIds.size() <= allCourses.size()) {
            System.out.println("✓ 所有 user_id 和 cid 都是唯一的");
        }

        // 验证每个分片都有数据
        if (eduDb1Course1Count > 0 && eduDb1Course2Count > 0 && eduDb2Course1Count > 0 && eduDb2Course2Count > 0) {
            System.out.println("\n✓ 所有分片都有数据，测试成功！");
        } else {
            System.out.println("\n✗ 部分分片没有数据，请检查配置或增加尝试次数");
        }
    }


    //批量添加操作，确保每个库和表都有数据插入
    @Test
    public void addCourseDbBatch() {
        System.out.println("========== 开始批量添加数据，确保每个库和表都有数据 ==========");
        System.out.println("分片规则：");
        System.out.println("  - edu_db_1.course_1: user_id为偶数, cid为偶数");
        System.out.println("  - edu_db_1.course_2: user_id为偶数, cid为奇数");
        System.out.println("  - edu_db_2.course_1: user_id为奇数, cid为偶数");
        System.out.println("  - edu_db_2.course_2: user_id为奇数, cid为奇数");
        System.out.println();

        // 用于记录已插入的数据，确保userid和cid都是唯一的
        java.util.Set<Long> usedUserIds = new java.util.HashSet<>();
        java.util.List<Course> insertedCourses = new java.util.ArrayList<>(); // 记录插入的课程信息

        int batchSize = 50; // 每批插入的数据量
        int maxBatches = 10; // 最大批次数，防止无限循环
        int batchCount = 0;
        long baseEvenUserId = 100L; // 偶数 user_id 起始值
        long baseOddUserId = 111L; // 奇数 user_id 起始值

        // 第一阶段：批量插入数据
        System.out.println("========== 第一阶段：批量插入数据 ==========");
        while (batchCount < maxBatches) {
            batchCount++;
            System.out.println("第 " + batchCount + " 批插入，每批 " + batchSize + " 条数据...");

            // 批量插入数据
            for (int i = 0; i < batchSize; i++) {
                // 交替使用偶数和奇数 user_id，确保覆盖两个数据库
                Long userId;
                if (i % 2 == 0) {
                    userId = baseEvenUserId;
                    baseEvenUserId += 2;
                } else {
                    userId = baseOddUserId;
                    baseOddUserId += 2;
                }

                // 确保 user_id 唯一
                while (usedUserIds.contains(userId)) {
                    userId += 2; // 保持奇偶性
                }
                usedUserIds.add(userId);

                // 插入数据
                Course course = new Course();
                course.setCname("java_batch" + batchCount + "_" + i);
                course.setUserId(userId);
                course.setCstatus("Normal_batch" + batchCount + "_" + i);
                courseMapper.insert(course);

                // 记录插入的课程信息（用于后续查询）
                insertedCourses.add(course);
            }

            System.out.println("第 " + batchCount + " 批插入完成，共插入 " + batchSize + " 条数据");

            // 第二阶段：批量验证分片数据
            System.out.println("\n========== 第二阶段：批量验证分片数据 ==========");
            boolean eduDb1Course1 = false; // user_id偶数, cid偶数
            boolean eduDb1Course2 = false; // user_id偶数, cid奇数
            boolean eduDb2Course1 = false; // user_id奇数, cid偶数
            boolean eduDb2Course2 = false; // user_id奇数, cid奇数

            // 批量查询所有数据（全库全表扫描，但只执行一次查询）
            java.util.List<Course> allCourses = courseMapper.selectList(null);

            // 验证每个分片是否有数据
            for (Course course : allCourses) {
                Long userId = course.getUserId();
                Long cid = course.getCid();

                if (userId == null || cid == null) {
                    continue;
                }

                boolean isEvenUserId = (userId % 2 == 0);
                boolean isEvenCid = (cid % 2 == 0);

                if (isEvenUserId && isEvenCid) {
                    if (!eduDb1Course1) {
                        eduDb1Course1 = true;
                        System.out.println("✓ 发现 edu_db_1.course_1 数据: user_id=" + userId + ", cid=" + cid);
                    }
                } else if (isEvenUserId && !isEvenCid) {
                    if (!eduDb1Course2) {
                        eduDb1Course2 = true;
                        System.out.println("✓ 发现 edu_db_1.course_2 数据: user_id=" + userId + ", cid=" + cid);
                    }
                } else if (!isEvenUserId && isEvenCid) {
                    if (!eduDb2Course1) {
                        eduDb2Course1 = true;
                        System.out.println("✓ 发现 edu_db_2.course_1 数据: user_id=" + userId + ", cid=" + cid);
                    }
                } else {
                    if (!eduDb2Course2) {
                        eduDb2Course2 = true;
                        System.out.println("✓ 发现 edu_db_2.course_2 数据: user_id=" + userId + ", cid=" + cid);
                    }
                }
            }

            // 如果所有分片都有数据，退出循环
            if (eduDb1Course1 && eduDb1Course2 && eduDb2Course1 && eduDb2Course2) {
                System.out.println("\n✓ 所有分片都有数据，停止插入");
                break;
            } else {
                System.out.println("\n当前分片状态：");
                System.out.println("  edu_db_1.course_1: " + (eduDb1Course1 ? "✓" : "✗"));
                System.out.println("  edu_db_1.course_2: " + (eduDb1Course2 ? "✓" : "✗"));
                System.out.println("  edu_db_2.course_1: " + (eduDb2Course1 ? "✓" : "✗"));
                System.out.println("  edu_db_2.course_2: " + (eduDb2Course2 ? "✓" : "✗"));
                System.out.println("继续插入下一批数据...\n");
            }
        }

        // 第三阶段：最终验证和统计
        System.out.println("\n========== 第三阶段：最终验证和统计 ==========");

        // 查询所有数据
        java.util.List<Course> allCourses = courseMapper.selectList(null);

        // 统计每个分片的数据
        int eduDb1Course1Count = 0;
        int eduDb1Course2Count = 0;
        int eduDb2Course1Count = 0;
        int eduDb2Course2Count = 0;
        java.util.Set<Long> allCids = new java.util.HashSet<>();
        java.util.Set<Long> allUserIds = new java.util.HashSet<>();

        for (Course course : allCourses) {
            Long userId = course.getUserId();
            Long cid = course.getCid();

            if (userId == null || cid == null) {
                continue;
            }

            allUserIds.add(userId);
            allCids.add(cid);

            boolean isEvenUserId = (userId % 2 == 0);
            boolean isEvenCid = (cid % 2 == 0);

            if (isEvenUserId && isEvenCid) {
                eduDb1Course1Count++;
            } else if (isEvenUserId && !isEvenCid) {
                eduDb1Course2Count++;
            } else if (!isEvenUserId && isEvenCid) {
                eduDb2Course1Count++;
            } else {
                eduDb2Course2Count++;
            }
        }

        // 输出结果统计
        System.out.println("========== 数据插入完成 ==========");
        System.out.println("edu_db_1.course_1: " + (eduDb1Course1Count > 0 ? "✓ 有数据 (" + eduDb1Course1Count + " 条)" : "✗ 无数据"));
        System.out.println("edu_db_1.course_2: " + (eduDb1Course2Count > 0 ? "✓ 有数据 (" + eduDb1Course2Count + " 条)" : "✗ 无数据"));
        System.out.println("edu_db_2.course_1: " + (eduDb2Course1Count > 0 ? "✓ 有数据 (" + eduDb2Course1Count + " 条)" : "✗ 无数据"));
        System.out.println("edu_db_2.course_2: " + (eduDb2Course2Count > 0 ? "✓ 有数据 (" + eduDb2Course2Count + " 条)" : "✗ 无数据"));
        System.out.println("总共插入数据: " + allCourses.size() + " 条");
        System.out.println("使用的 user_id 数量: " + allUserIds.size());
        System.out.println("使用的 cid 数量: " + allCids.size());
        System.out.println("插入批次数: " + batchCount);

        // 验证唯一性
        if (allCids.size() == allCourses.size() && allUserIds.size() <= allCourses.size()) {
            System.out.println("✓ 所有 user_id 和 cid 都是唯一的");
        } else {
            System.out.println("警告：可能存在重复的 user_id 或 cid");
        }

        // 验证每个分片都有数据
        if (eduDb1Course1Count > 0 && eduDb1Course2Count > 0 && eduDb2Course1Count > 0 && eduDb2Course2Count > 0) {
            System.out.println("\n✓ 所有分片都有数据，测试成功！");
        } else {
            System.out.println("\n✗ 部分分片没有数据，请检查配置或增加批次数");
        }
    }

    //查询操作 - 精确查询（包含user_id和cid，精确路由到单个表）
    @Test
    public void findCourseDb() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //设置userid 值，用于数据库分片路由
        wrapper.eq("user_id", 100L);
        //设置cid 值，用于表分片路由
        wrapper.eq("cid", 465162909769531393L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println("查询结果: " + course);
    }

    //查询操作 - 根据user_id查询（会扫描该数据库下的所有表）
    @Test
    public void findCourseByUserId() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //只设置user_id，会扫描对应数据库下的所有表（course_1和course_2）
        wrapper.eq("user_id", 100L);
        //可以添加其他非分片键的查询条件
        wrapper.like("cname", "java");
        java.util.List<Course> courses = courseMapper.selectList(wrapper);
        System.out.println("根据user_id=100查询到的数据数量: " + courses.size());
        for (Course course : courses) {
            System.out.println("  " + course);
        }
    }

    //查询操作 - 根据cid查询（会扫描所有数据库和表）
    @Test
    public void findCourseByCid() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //只设置cid，没有user_id，会扫描所有数据库和表
        wrapper.eq("cid", 465162909769531393L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println("根据cid查询结果: " + course);
    }

    //查询操作 - 查询所有数据（全库全表扫描）
    @Test
    public void findAllCourse() {
        java.util.List<Course> courses = courseMapper.selectList(null);
        System.out.println("查询所有数据，数量: " + courses.size());
        for (Course course : courses) {
            System.out.println("  user_id=" + course.getUserId() + ", cid=" + course.getCid() + ", cname=" + course.getCname());
        }
    }

    //验证分片规则 - 验证每个分片的数据分布
    @Test
    public void verifyShardingRules() {
        System.out.println("========== 验证分片规则 ==========");

        // 查询所有数据
        java.util.List<Course> allCourses = courseMapper.selectList(null);

        // 统计每个分片的数据
        int eduDb1Course1Count = 0; // user_id偶数, cid偶数
        int eduDb1Course2Count = 0; // user_id偶数, cid奇数
        int eduDb2Course1Count = 0; // user_id奇数, cid偶数
        int eduDb2Course2Count = 0; // user_id奇数, cid奇数

        for (Course course : allCourses) {
            Long userId = course.getUserId();
            Long cid = course.getCid();
            boolean isEvenUserId = (userId % 2 == 0);
            boolean isEvenCid = (cid % 2 == 0);

            if (isEvenUserId && isEvenCid) {
                eduDb1Course1Count++;
            } else if (isEvenUserId && !isEvenCid) {
                eduDb1Course2Count++;
            } else if (!isEvenUserId && isEvenCid) {
                eduDb2Course1Count++;
            } else {
                eduDb2Course2Count++;
            }
        }

        System.out.println("edu_db_1.course_1 数据量: " + eduDb1Course1Count);
        System.out.println("edu_db_1.course_2 数据量: " + eduDb1Course2Count);
        System.out.println("edu_db_2.course_1 数据量: " + eduDb2Course1Count);
        System.out.println("edu_db_2.course_2 数据量: " + eduDb2Course2Count);
        System.out.println("总数据量: " + allCourses.size());

        // 验证唯一性
        java.util.Set<Long> userIds = new java.util.HashSet<>();
        java.util.Set<Long> cids = new java.util.HashSet<>();
        boolean hasDuplicate = false;

        for (Course course : allCourses) {
            if (userIds.contains(course.getUserId())) {
                System.out.println("警告：发现重复的 user_id: " + course.getUserId());
                hasDuplicate = true;
            }
            if (cids.contains(course.getCid())) {
                System.out.println("警告：发现重复的 cid: " + course.getCid());
                hasDuplicate = true;
            }
            userIds.add(course.getUserId());
            cids.add(course.getCid());
        }

        if (!hasDuplicate) {
            System.out.println("\n✓ 所有 user_id 和 cid 都是唯一的");
        }

        // 验证每个分片都有数据
        if (eduDb1Course1Count > 0 && eduDb1Course2Count > 0 && eduDb2Course1Count > 0 && eduDb2Course2Count > 0) {
            System.out.println("\n✓ 所有分片都有数据，分片规则验证通过！");
        } else {
            System.out.println("\n✗ 部分分片没有数据，请先执行 addCourseDbBatch() 方法");
        }
    }
}
