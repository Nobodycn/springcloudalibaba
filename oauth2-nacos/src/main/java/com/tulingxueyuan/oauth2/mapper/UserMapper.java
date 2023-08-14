package com.tulingxueyuan.oauth2.mapper;

import com.tulingxueyuan.oauth2.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Fox
 */
@Mapper
public interface UserMapper {

    @Select("select * from tb_user where username=#{username}")
    User getByUsername(String username);
}
