package com.wj.vegetablebackend.controller;

import com.mybatisflex.core.paginate.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wj.vegetablebackend.model.entity.ShoppingCart;
import com.wj.vegetablebackend.service.ShoppingCartService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 购物车 控制层。
 *
 * @author wj
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * 保存购物车。
     *
     * @param shoppingCart 购物车
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.save(shoppingCart);
    }

    /**
     * 根据主键删除购物车。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return shoppingCartService.removeById(id);
    }

    /**
     * 根据主键更新购物车。
     *
     * @param shoppingCart 购物车
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.updateById(shoppingCart);
    }

    /**
     * 查询所有购物车。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<ShoppingCart> list() {
        return shoppingCartService.list();
    }

    /**
     * 根据主键获取购物车。
     *
     * @param id 购物车主键
     * @return 购物车详情
     */
    @GetMapping("getInfo/{id}")
    public ShoppingCart getInfo(@PathVariable Long id) {
        return shoppingCartService.getById(id);
    }

    /**
     * 分页查询购物车。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<ShoppingCart> page(Page<ShoppingCart> page) {
        return shoppingCartService.page(page);
    }

}
