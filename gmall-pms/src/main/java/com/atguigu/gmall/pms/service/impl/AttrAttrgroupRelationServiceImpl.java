package com.atguigu.gmall.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;

import com.atguigu.gmall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gmall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gmall.pms.service.AttrAttrgroupRelationService;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    public Object deleteByGroupIdAndAttrId(List<AttrAttrgroupRelationEntity> entities) {
        entities.forEach(attrAttrgroupRelationEntity -> {
            Long attrGroupId = attrAttrgroupRelationEntity.getAttrGroupId();
            boolean attrGroupCondition = attrGroupId != null;
            Long attrId = attrAttrgroupRelationEntity.getAttrId();
            boolean attrCondition = attrId != null;
            Wrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<AttrAttrgroupRelationEntity>().lambda()
                    .eq(attrGroupCondition, AttrAttrgroupRelationEntity::getAttrGroupId, attrGroupId)
                    .eq(attrCondition, AttrAttrgroupRelationEntity::getAttrId, attrId);
            this.remove(wrapper);
        });
        return true;
    }

}