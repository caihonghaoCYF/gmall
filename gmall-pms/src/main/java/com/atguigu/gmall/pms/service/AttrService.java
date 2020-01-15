package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.entity.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 商品属性
 *
 * @author hao
 * @email 929319303@qq.com
 * @date 2020-01-08 22:16:34
 */
public interface AttrService extends IService<AttrEntity> {

    PageVo queryPage(QueryCondition params);

    PageVo getByCidAndType(QueryCondition queryCondition,Integer type, Long cid);

    void saveAttrVo(AttrVo attrVo);
}

