package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.gmall.pms.entity.vo.GroupAttrWithRelation;
import com.atguigu.gmall.pms.service.AttrAttrgroupRelationService;
import com.atguigu.gmall.pms.service.AttrService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {


    @Autowired
    AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Autowired
    AttrService attrService;


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

    @Override
    public GroupAttrWithRelation getGroupAttrWithRelationByGid(Long gid) {

        GroupAttrWithRelation groupAttrWithRelation = new GroupAttrWithRelation();

        AttrGroupEntity attrGroupEntity = this.getById(gid);

        if (null == attrGroupEntity){ return groupAttrWithRelation; }

        BeanUtils.copyProperties(attrGroupEntity, groupAttrWithRelation);

        Wrapper<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntityWrapper = new QueryWrapper<AttrAttrgroupRelationEntity>()
                .lambda()
                .eq(AttrAttrgroupRelationEntity::getAttrGroupId, gid);

        List<AttrAttrgroupRelationEntity> attrGroupRelationEntities = attrAttrgroupRelationService.list(attrAttrgroupRelationEntityWrapper);

        if (CollectionUtils.isEmpty(attrGroupRelationEntities)){ return groupAttrWithRelation; }

        groupAttrWithRelation.setRelations(attrGroupRelationEntities);

        List<Long> attrIds = attrGroupRelationEntities.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());

        List<AttrEntity> attrEntities = new ArrayList<>(attrService.listByIds(attrIds));

        groupAttrWithRelation.setAttrEntities(attrEntities);

        return groupAttrWithRelation;
    }

}