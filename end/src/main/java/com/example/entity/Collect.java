package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("collect")
public class Collect extends Model<Collect> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
      * 商品名称 
      */
    private String goodsName;

    /**
      * 商品图片 
      */
    private String goodsImg;

    /**
      * 商品id 
      */
    private String goodsId;

    /**
      * 用户id 
      */
    private String userId;

    /**
      * 收藏时间 
      */
    private String createTime;

}