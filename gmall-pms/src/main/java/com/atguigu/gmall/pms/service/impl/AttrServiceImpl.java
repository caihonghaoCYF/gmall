package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gmall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gmall.pms.entity.vo.AttrVo;
import com.atguigu.gmall.pms.service.AttrAttrgroupRelationService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;

import com.atguigu.gmall.pms.dao.AttrDao;
import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.gmall.pms.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {


    @Autowired
    AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Autowired
    AttrDao attrDao;
    @Autowired
    AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo getByCidAndType(QueryCondition queryCondition,Integer type, Long cid) {
        boolean condition = cid != null;
        Wrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().lambda()
                .eq(condition, AttrEntity::getCatelogId, cid)
                .eq(AttrEntity::getValueType, type);
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(queryCondition),
                wrapper
        );
        return new PageVo(page);
    }

    @Override
    @Transactional
    public void saveAttrVo(AttrVo attrVo) {
        this.attrDao.insert(attrVo);
        Long attrId = attrVo.getAttrId();
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(attrVo.getAttrGroupId());
        relationEntity.setAttrId(attrId);
        relationEntity.setAttrSort(0);
        this.attrAttrgroupRelationDao.insert(relationEntity);
    }

}