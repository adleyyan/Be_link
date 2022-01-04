package com.hq.bms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hq.bms.model.SysUser;
import com.hq.bms.mapper.SysUserMapper;
import com.hq.bms.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author adley.yan
 * @since 2021-12-29
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .ge("username", s);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }
}
