package com.example.service;

import com.example.entity.Banner;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.BannerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BannerService extends ServiceImpl<BannerMapper, Banner> {

    @Resource
    private BannerMapper bannerMapper;

}
