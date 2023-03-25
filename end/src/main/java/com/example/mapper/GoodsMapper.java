package com.example.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {

    IPage<Goods> findPage(Page<Goods> page,  @Param("name") String name);

    @Select("select * from goods where category_id = #{id}")
    IPage<Goods> pageByCategory(Page<Goods> page, @Param("id") Long id);

    List<Goods> getRecommend();

    @Select("select * from goods order by sales desc")
    List<Goods> sales();

    List<Goods> findAll();

}
