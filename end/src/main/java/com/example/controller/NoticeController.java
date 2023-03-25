package com.example.controller;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.example.common.Result;
import com.example.service.UserService;
import com.example.entity.Notice;
import com.example.service.NoticeService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private UserService userService;

    public User getUser() {
        String token = request.getHeader("token");
        String username = JWT.decode(token).getAudience().get(0);
        return userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    }

    @PostMapping
    public Result<?> save(@RequestBody Notice notice) {
        notice.setTime(DateUtil.formatDateTime(new Date()));
        return Result.success(noticeService.save(notice));
    }

    @PutMapping
    public Result<?> update(@RequestBody Notice notice) {
        return Result.success(noticeService.updateById(notice));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        noticeService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(noticeService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(noticeService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Notice> query = Wrappers.<Notice>lambdaQuery().like(Notice::getTitle, name).orderByDesc(Notice::getId);;
        return Result.success(noticeService.page(new Page<>(pageNum, pageSize), query));
    }

}
