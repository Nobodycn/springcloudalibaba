package com.tulingxueyuan.sharding;

import com.tulingxueyuan.sharding.entity.Course;
import com.tulingxueyuan.sharding.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 单元测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingTest {

    @Resource
    private CourseMapper courseMapper;

    @Test
    public void addCourse() {
        for (int i = 0; i < 100; i++) {
            Course course = new Course();
            course.setCname("Java");
            course.setUserId(Long.valueOf("" + (100 + i)));
            course.setCstatus("1");
            courseMapper.insert(course);
        }
    }
}
