package com.tulingxueyuan.sharding;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tulingxueyuan.sharding.entity.Course;
import com.tulingxueyuan.sharding.entity.Dict;
import com.tulingxueyuan.sharding.entity.User;
import com.tulingxueyuan.sharding.mapper.CourseMapper;
import com.tulingxueyuan.sharding.mapper.DictMapper;
import com.tulingxueyuan.sharding.mapper.UserMapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 单元测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingTest {

    @Resource
    private CourseMapper courseMapper;
    @Resource
    private DictMapper dictMapper;
    @Resource
    private UserMapper userMapper;

    @Test
    public void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCname("Java");
            course.setUserId(Long.valueOf("" + (100)));
            course.setCstatus("1");
            courseMapper.insert(course);
        }
    }

    @Test
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public void updateCourse() {
        Course c = new Course();
        UpdateWrapper<Course> wrapper = new UpdateWrapper<>();
        wrapper.set("user_id", "5");
        courseMapper.update(c, wrapper);
    }

    //查询同样是按照分片规则。
    //这里会测试多种规则。
    @Test
    public void queryCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>();
        //强制只查course_1表
        HintManager hintManager = HintManager.getInstance();
        //注意这两个属性，dataSourceBaseShardingValue用于强制分库
        // 强制查m1数据源
        hintManager.addDatabaseShardingValue("course","1");
        // 强制查course_1表
        hintManager.addTableShardingValue("course","1");
        wrapper.eq("cid", 1697296653531271169L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }

    @Test
    public void queryCourseRange() {
        //select * from course where cid between xxx and xxx
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.between("cid", 1697296653531271169L, 1697296654378520578L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }

    //application01的inline策略，对于这种排序查询，也必须要带上cid的查询条件，否则解析出来的实际SQL就会出问题。
    @Test
    public void queryCourseOrder() {
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>();
        wrapper.orderByDesc("user_id");
        wrapper.eq("cid", 546378370871459840L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

    //复杂分片策略，所有查询条件只能有一个统一的类型。
    @Test
    public void queryCourdeComplex() {
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>();
        wrapper.orderByDesc("user_id");
        wrapper.in("cid", 1697296653531271169L, 1697296654378520578L);
        // 多个字段查询。standard不足以支持，需要使用到complex分片算法
        wrapper.between("user_id", 3L, 1008L);
//        wrapper.and(courseQueryWrapper -> courseQueryWrapper.between("user_id","3","8"));
        List<Course> course = courseMapper.selectList(wrapper);
        System.out.println(course);
    }

    //强制路由策略。脱离SQL自己指定分片策略。
    @Test
    public void queryCourseByHint() {
        //强制只查course_1表
        HintManager hintManager = HintManager.getInstance();
        //注意这两个属性，dataSourceBaseShardingValue用于强制分库
        // 强制查m1数据源
        hintManager.addDatabaseShardingValue("course", "1");
        // 强制查course_1表
        hintManager.addTableShardingValue("course", "1");

        List<Course> courses = courseMapper.selectList(null);
        courses.forEach(course -> System.out.println(course));
        //线程安全，所有用完要注意关闭。
        hintManager.close();
    }

    @Test
    public void addUser() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("user_" + i);
            user.setUstatus(String.valueOf(i % 2 + 1));
            userMapper.insert(user);
        }
    }

    @Test
    public void queryUser() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }


    /**
     * 绑定表查询
     * 这个关联查询在application03.properties配置中会报错。
     * 因为他会将user表也一起作为广播表处理。
     * 而在application04.properties中配置了绑定表后，t_user_1表将只和t_dict_1表联合，而不会和t_dict_2表联合。
     */
    @Test
    public void queryUserStatus() {
        List<User> users = userMapper.queryUserStatus();
        for (User user : users) {
            System.out.println(user);
        }
    }

    //t_dict公共表测试
    @Test
    public void addDict() {
        Dict dict = new Dict();
        dict.setUstatus("1");
        dict.setUvalue("正常");
        dictMapper.insert(dict);

        Dict dict2 = new Dict();
        dict2.setUstatus("2");
        dict2.setUvalue("异常");
        dictMapper.insert(dict2);
    }

    @Test
    public void queryDict() {
        QueryWrapper<Dict> wrapper = new QueryWrapper<Dict>();
        wrapper.eq("ustatus", "1");
        List<Dict> dicts = dictMapper.selectList(wrapper);
        dicts.forEach(System.out::println);
    }

    //公共表修改测试
    @Test
    public void updateDict() {
        Dict dict = new Dict();
        dict.setUstatus("1");
        dict.setUvalue("Normal");

        UpdateWrapper<Dict> wrapper = new UpdateWrapper<Dict>();
        wrapper.eq("ustatus", dict.getUstatus());
        dictMapper.update(dict, wrapper);
    }
}
