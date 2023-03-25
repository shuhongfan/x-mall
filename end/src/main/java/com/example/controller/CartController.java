package com.example.controller;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Cart;
import com.example.entity.User;
import com.example.service.CartService;
import com.example.service.UserService;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Resource
    private CartService cartService;
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
    public Result<?> save(@RequestBody Cart cart) {
        cart.setCreateTime(DateUtil.now());
        // 加入购物车，相同的商品累加
        Cart userCart = cartService.getOne(Wrappers.<Cart>lambdaQuery().eq(Cart::getGoodsId, cart.getGoodsId()).eq(Cart::getUserId, cart.getUserId()));
        if (userCart != null) {
            userCart.setCount(cart.getCount() + userCart.getCount());
            cartService.updateById(userCart);
        } else {
            // 不同商品添加新的购物车记录
            cartService.save(cart);
        }
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Cart cart) {
        cartService.updateById(cart);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        cartService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(cartService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() throws JSONException {
        List<Cart> carts = new ArrayList<>();
        User user = getUser();
        if (user != null) {
            carts = cartService.list(Wrappers.<Cart>lambdaQuery().eq(Cart::getUserId, getUser().getId()));
        }
        return Result.success(cartService.findAll(carts));
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Cart> query = Wrappers.<Cart>lambdaQuery().orderByDesc(Cart::getId);

        IPage<Cart> page = cartService.page(new Page<>(pageNum, pageSize), query);
        return Result.success(page);
    }

}
