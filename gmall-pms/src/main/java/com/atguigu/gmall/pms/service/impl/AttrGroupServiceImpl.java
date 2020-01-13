package com.atguigu.gmall.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;

import com.atguigu.gmall.pms.dao.AttrGroupDao;
import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import com.atguigu.gmall.pms.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo getAttrGroupByCid(QueryCondition condition, Long cid) {
        boolean cidCondition = cid != null;
        Wrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>().lambda().eq(cidCondition, AttrGroupEntity::getCatelogId, cid);
        IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(condition), wrapper);
        return new PageVo(page);
    }

}