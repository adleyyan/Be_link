package com.hq.bms.service.impl;

import cn.hutool.Hutool;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.HashUtil;
import com.hq.bms.excel.ExportUser;
import com.hq.bms.model.User;
import com.hq.bms.mapper.UserMapper;
import com.hq.bms.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-12-21
 */
@Cacheable(cacheNames = {"userService"})
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public List<ExportUser> getUserList() {
        List<User> userList = userMapper.findAllUser();
        List<ExportUser> exportUsers = new ArrayList<>();
        for (User user : userList) {
            ExportUser exportUser = new ExportUser();
            exportUser.setUsername(user.getName());
            exportUser.setCreateTime(DateUtil.date(System.nanoTime()));
            exportUsers.add(exportUser);
        }
        return exportUsers;
    }
}
