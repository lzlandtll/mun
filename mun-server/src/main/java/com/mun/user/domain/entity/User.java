package com.mun.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "USERS", autoResultMap = true)
public class User {
    @TableId(value = "USER_ID")
    private String userId;
    @TableField("PASSWORD")
    private String password;
    @TableField("USER_NAME")
    private String userName;
    @TableField("ICON_URL")
    private String iconUrl;
}
