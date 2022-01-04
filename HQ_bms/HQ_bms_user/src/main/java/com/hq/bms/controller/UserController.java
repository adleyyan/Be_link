package com.hq.bms.controller;


import cn.hutool.Hutool;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import com.hq.bms.common.exception.MyException;
import com.hq.bms.excel.ExportUser;
import com.hq.bms.model.User;
import com.hq.bms.service.IUserService;
import com.hq.bms.util.EasyPoiUtils;
import com.hq.bms.util.ExcetionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author adley.yan
 * @since 2021-12-21
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/test")
    @Transactional
    public void transfor(){
        User u =new User();
        u.setId(13);
        u.setName("麒麟鱼");
        u.setAge(16);
        u.setIdCard("8888888888888");
       boolean save = iUserService.save(u);
       try {
           int a = 10/0;
       }catch (Exception e){
           log.debug(ExcetionUtil.getMessage(e));
       }

    }
    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public Object transfor2(String name ){
//        QueryWrapper wrapper = new QueryWrapper<User>();
//        wrapper.like("name",name);
        String allUser = stringRedisTemplate.opsForValue().get("allUser");
        if (Strings.isNullOrEmpty(allUser)){
            log.info("redis缓存数据为"+allUser.length());
            List<User> list = iUserService.findAllUser();
            stringRedisTemplate.opsForValue().set("allUser", JSON.toJSONString(list));
            return list;
        }
        log.info("redis缓存数据为"+allUser.length());

        return allUser;
    }
    @PostMapping("/test3")
    public @ResponseBody User transfor3(){
        User u =new User();
        u.setId(11);
        u.setName("飞鸽");
        u.setAge(45);
        u.setIdCard("88977788877889899889988");
        return u;
    }

    /**
     * 用户数据导入
     * @param file
     * @return
     */
    @RequestMapping("/import/users")
    @ResponseBody
    public List<ExportUser> importUsers(@RequestParam MultipartFile file) {
        return EasyPoiUtils.importExcel(file, ExportUser.class);
    }

    /**
     * 用户数据导出
     * @param response
     */
    @RequestMapping(value = "/export/users", method = RequestMethod.GET)
    public void exportUsers(HttpServletResponse response) {
        List<ExportUser> userList = iUserService.getUserList();
        EasyPoiUtils.exportExcel(userList, "用户列表", "用户报表", ExportUser.class, "用户明细报表.xls", response);
    }

}
