package com.sdw.securitydemo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdw.securitydemo1.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/5 9:57
 * @Version 1.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
