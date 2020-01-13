package com.atguigu.gmall.pms.service;

import com.atguigu.core.bean.Resp;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;


/**
 * 属性分组
 *
 * @author hao
 * @email 929319303@qq.com
 * @date 2020-01-08 22:16:34
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageVo queryPage(QueryCondition params);

    PageVo getAttrGroupByCid(QueryCondition condition, Long cid);
}

