package com.example.service;

import com.example.entity.Address;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.AddressMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AddressService extends ServiceImpl<AddressMapper, Address> {

    @Resource
    private AddressMapper addressMapper;

}
