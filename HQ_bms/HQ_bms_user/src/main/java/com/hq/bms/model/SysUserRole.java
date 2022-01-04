package com.hq.bms.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author adley.yan
 * @since 2021-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUserRole extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId("UID")
    private Integer uid;

    /**
     * 角色编号
     */
    @TableField("RID")
    private Integer rid;


}
