package com.atguigu.gmall.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;

import com.atguigu.gmall.pms.dao.CategoryDao;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmall.pms.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    public List<CategoryEntity> queryByLevelOrCategoryId(Integer level, Long parentCid) {

        boolean levelCondition = level != 0;
        boolean parentCidCondition = parentCid != null;

        Wrapper<CategoryEntity> queryWrapper = new QueryWrapper<CategoryEntity>().lambda()
                .eq(levelCondition, CategoryEntity::getCatLevel, level)
                .eq(parentCidCondition, CategoryEntity::getParentCid, parentCid);
        return baseMapper.selectList(queryWrapper);
    }

}