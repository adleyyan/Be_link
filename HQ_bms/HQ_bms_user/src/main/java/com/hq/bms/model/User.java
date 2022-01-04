package com.hq.bms.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends Model {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer age;

    @TableField("id_card")
    private String idCard;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
