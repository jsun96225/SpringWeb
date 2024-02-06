package com.springweb.main.controller;

import com.springweb.main.cache.OrderStatusLocalCache;
import com.springweb.main.entity.Monitor;
import com.springweb.main.model.OrderDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StatusController {

    @GetMapping("/status/{orderId}")
    public String status(@PathVariable String orderId, Model model) {
        OrderDetail orderDetail = OrderStatusLocalCache.getOrderDetailByOrderId(orderId);
        model.addAttribute("orderDetail", orderDetail);
        return "status";
    }
}
