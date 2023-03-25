package com.example.controller;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.example.common.Result;
import com.example.service.UserService;
import com.example.entity.OrderGoods;
import com.example.service.OrderGoodsService;
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
@RequestMapping("/api/orderGoods")
public class OrderGoodsController {
    @Resource
    private OrderGoodsService orderGoodsService;
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
    public Result<?> save(@RequestBody OrderGoods orderGoods) {
        orderGoodsService.save(orderGoods);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody OrderGoods orderGoods) {
        orderGoodsService.updateById(orderGoods);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        orderGoodsService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(orderGoodsService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        List<OrderGoods> list = orderGoodsService.list();
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<OrderGoods> query = Wrappers.<OrderGoods>lambdaQuery().orderByDesc(OrderGoods::getId);
        IPage<OrderGoods> page = orderGoodsService.page(new Page<>(pageNum, pageSize), query);
        return Result.success(page);
    }

}
