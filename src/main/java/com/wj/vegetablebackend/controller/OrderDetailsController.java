package com.wj.vegetablebackend.controller;

import cn.hutool.core.collection.CollUtil;
import com.wj.vegetablebackend.annotation.AuthCheck;
import com.wj.vegetablebackend.common.BaseResponse;
import com.wj.vegetablebackend.common.ResultUtils;
import com.wj.vegetablebackend.constant.UserConstant;
import com.wj.vegetablebackend.exception.ErrorCode;
import com.wj.vegetablebackend.exception.ThrowUtils;
import com.wj.vegetablebackend.model.vo.OrderDetailsVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wj.vegetablebackend.model.entity.OrderDetails;
import com.wj.vegetablebackend.service.OrderDetailsService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单详情 控制层。
 *
 * @author wj
 */
@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {

    @Resource
    private OrderDetailsService orderDetailsService;

    /**
     * 根据 订单 id 获取订单
     */
    @GetMapping("/getByOrderId")
    public BaseResponse<List<OrderDetailsVO>> getOrderDetailsByOrderId(long orderId) {
        ThrowUtils.throwIf(orderId <= 0, ErrorCode.PARAMS_ERROR);
        List<OrderDetailsVO> orderDetailsList = orderDetailsService.getOrderDetailsByOrderId(orderId);
        ThrowUtils.throwIf(CollUtil.isEmpty(orderDetailsList), ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(orderDetailsList);
    }

}
