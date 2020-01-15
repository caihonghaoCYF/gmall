package com.atguigu.gmall.pms.controller;

import java.util.Arrays;
import java.util.Map;


import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.pms.entity.vo.AttrVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.gmall.pms.service.AttrService;




/**
 * 商品属性
 *
 * @author hao
 * @email 929319303@qq.com
 * @date 2020-01-08 22:16:34
 */
@Api(tags = "商品属性 管理")
@RestController
@RequestMapping("pms/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;



    @ApiOperation("保存商品属性")
    @PostMapping("save")
    public Resp<Object> saveAttr(@RequestBody AttrVo attrVo){
        Assert.notNull(attrVo.getAttrGroupId(), "属性组ID不能为空");
        attrService.saveAttrVo(attrVo);
        return Resp.ok(null);
    }


    @ApiOperation("根据属性类型和分类id获取属性数据")
    @GetMapping
    public Resp<PageVo>queryAttrByTypeAndCategory(QueryCondition queryCondition,
                                                 @RequestParam(value = "type", defaultValue = "0") Integer type,
                                                 @RequestParam(value = "cid", required = false) Long cid){
        return Resp.ok(attrService.getByCidAndType(queryCondition, type, cid));
    }

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:attr:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = attrService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{attrId}")
    @PreAuthorize("hasAuthority('pms:attr:info')")
    public Resp<AttrEntity> info(@PathVariable("attrId") Long attrId){
		AttrEntity attr = attrService.getById(attrId);

        return Resp.ok(attr);
    }

//    /**
//     * 保存
//     */
//    @ApiOperation("保存")
//    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('pms:attr:save')")
//    public Resp<Object> save(@RequestBody AttrEntity attr){
//		attrService.save(attr);
//
//        return Resp.ok(null);
//    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('pms:attr:update')")
    public Resp<Object> update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('pms:attr:delete')")
    public Resp<Object> delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return Resp.ok(null);
    }

}
