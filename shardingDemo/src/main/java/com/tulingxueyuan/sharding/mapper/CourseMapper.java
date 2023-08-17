package com.tulingxueyuan.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.sharding.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
}
