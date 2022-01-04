package com.hq.bms.service;

import com.hq.bms.excel.ExportUser;
import com.hq.bms.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-12-21
 */
public interface IUserService extends IService<User> {

    List<User> findAllUser();
    List<ExportUser> getUserList();

}
