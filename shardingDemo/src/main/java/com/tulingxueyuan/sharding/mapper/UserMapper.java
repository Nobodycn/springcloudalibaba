package com.tulingxueyuan.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.sharding.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：楼兰
 * @date ：Created in 2020/11/12
 * @description:
 **/
public interface UserMapper extends BaseMapper<User> {

    @Select("select u.user_id,u.username,d.uvalue ustatus from user u left join t_dict d on u.ustatus = d.ustatus")
    public List<User> queryUserStatus();
}
