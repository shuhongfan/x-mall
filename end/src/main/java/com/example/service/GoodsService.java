package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Goods;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {

    @Resource
    private GoodsMapper goodsMapper;

    public IPage<Goods> findPage(Page<Goods> page, String name) {
        return goodsMapper.findPage(page, name);
    }

    public IPage<Goods> pageByCategory(Page<Goods> page, Long id) {
        return goodsMapper.pageByCategory(page, id);
    }

    public List<Goods> recommend() {
        return goodsMapper.getRecommend();
    }

    public List<Goods> sales() {
        return goodsMapper.sales();
    }

    public List<Goods> findAll() {
        return goodsMapper.findAll();
    }
}
