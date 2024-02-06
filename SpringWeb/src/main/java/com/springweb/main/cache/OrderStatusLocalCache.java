package com.springweb.main.cache;

import com.springweb.main.model.OrderDetail;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class OrderStatusLocalCache {

    private static final ConcurrentHashMap<String, OrderDetail> orderDetailMap = new ConcurrentHashMap<>();
    private OrderStatusLocalCache() {

    }

    public static void addOrderDetail(OrderDetail orderDetail) {
        orderDetailMap.put(orderDetail.getOrderId(), orderDetail);

    }

    public static OrderDetail getOrderDetailByOrderId(String orderId) {
        return orderDetailMap.getOrDefault(orderId, null);
    }
}
