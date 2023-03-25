package com.example.controller;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.example.common.Result;
import com.example.service.UserService;
import com.example.entity.Banner;
import com.example.service.BannerService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/banner")
public class BannerController {
    @Resource
    private BannerService bannerService;
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
    public Result<?> save(@RequestBody Banner banner) {
        bannerService.save(banner);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Banner banner) {
        bannerService.updateById(banner);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        bannerService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(bannerService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        List<Banner> list = bannerService.list();
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Banner> query = Wrappers.<Banner>lambdaQuery().orderByDesc(Banner::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(Banner::getUrl, name);
        }
        IPage<Banner> page = bannerService.page(new Page<>(pageNum, pageSize), query);
        return Result.success(page);
    }

}
