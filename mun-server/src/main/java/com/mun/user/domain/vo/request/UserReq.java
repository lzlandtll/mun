package com.mun.user.domain.vo.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: xxx
 * @Author: liuzhilin
 * @Date: 2023/9/26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
    private String userId;
    private String password;
}
