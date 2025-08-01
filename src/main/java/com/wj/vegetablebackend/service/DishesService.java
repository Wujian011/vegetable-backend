package com.wj.vegetablebackend.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.wj.vegetablebackend.model.dto.dishes.DishesQueryRequest;
import com.wj.vegetablebackend.model.entity.Dishes;
import com.wj.vegetablebackend.model.vo.DishesVO;

import java.util.List;

/**
 * 菜品 服务层。
 *
 * @author wj
 */
public interface DishesService extends IService<Dishes> {

    /**
     * 根据查询条件构造数据查询参数
     *
     * @param dishesQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(DishesQueryRequest dishesQueryRequest);


    /**
     * 获取脱敏后的分类信息
     *
     * @param dishes 分类信息
     * @return
     */
    DishesVO getDishesVO(Dishes dishes);

    /**
     * 获取脱敏后的分类信息（分页）
     *
     * @param dishesList 分类列表
     * @return
     */
    List<DishesVO> getDishesVOList(List<Dishes> dishesList);

}
