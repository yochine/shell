package me.zrxjava.system.modules.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.common.base.BaseEntity;

import java.time.LocalDateTime;

/**
 * 系统用户对象 sys_user
 * 
 * @author zrxjava
 * @date 2021-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@ApiModel(value="sys_user对象", description="系统用户")
public class User extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    /** 部门名称 */
    private Long deptId;

    /** 用户名 */
    private String username;

    /** 昵称 */
    private String nickName;

    /** 性别 */
    private String gender;

    /** 手机号码 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 头像地址 */
    private String avatarName;

    /** 头像真实路径 */
    private String avatarPath;

    /** 密码 */
    private String password;

    /** 是否为admin账号 */
    private Integer isAdmin;

    /** 状态：1启用、0禁用 */
    private Integer enabled;

    /** 修改密码的时间 */
    private LocalDateTime pwdResetTime;


}
