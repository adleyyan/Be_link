package com.hq.bms.mapper;

import com.hq.bms.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-12-21
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> findAllUser();

}
