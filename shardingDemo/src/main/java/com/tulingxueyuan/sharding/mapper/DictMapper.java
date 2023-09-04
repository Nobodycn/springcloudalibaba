package com.tulingxueyuan.sharding.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.sharding.entity.Dict;

/**
 * @author ：楼兰
 * @date ：Created in 2020/11/12
 * @description:
 **/

public interface DictMapper extends BaseMapper<Dict> {
    void update(Dict dict, UpdateWrapper<Dict> wrapper);
}
