package com.sdw.securitydemo1.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdw.securitydemo1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MyUserDetailsService
 * @Description TODO
 * @Author: 索德文
 * @date 2021/9/4 22:00
 * @Version 1.0
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 调用userMapper方法， 根据用户名查询数据库
        QueryWrapper<com.sdw.securitydemo1.entity.User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        com.sdw.securitydemo1.entity.User user = userMapper.selectOne(userQueryWrapper);

        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
        // 从查询数据库返回user对象， 得到用户名和密码，返回
        return new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), auths);
    }
}
