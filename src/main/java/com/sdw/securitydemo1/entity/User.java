package com.sdw.securitydemo1.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/4 22:22
 * @Version 1.0
 */
@Data
@AllArgsConstructor  // 有参构造
@NoArgsConstructor   // 无参构造
public class User {
    @TableId
    private Integer id;
    private String username;
    private String password;
}
