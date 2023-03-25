package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Log;
import com.example.entity.User;
import com.example.mapper.LogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class LogService extends ServiceImpl<LogMapper, Log> {

    @Resource
    private LogMapper logMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    private UserService userService;

    public User getUser() {
        try {
            String token = request.getHeader("token");
            String username = JWT.decode(token).getAudience().get(0);
            return userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日志记录
     *
     * @param content
     */
    public void log(String content) {
        Log log = new Log();
        log.setUser(getUser().getUsername());
        log.setTime(DateUtil.formatDateTime(new Date()));
        log.setIp(getIpAddress());
        log.setContent(content);
        save(log);
    }

    /**
     * 日志记录
     *
     * @param username
     * @param content
     */
    public void log(String username, String content) {
        Log log = new Log();
        log.setUser(username);
        log.setTime(DateUtil.formatDateTime(new Date()));
        log.setIp(getIpAddress());
        log.setContent(content);
        save(log);
    }


    /**
     * 描述：获取IP地址
     */
    public String getIpAddress() {

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
