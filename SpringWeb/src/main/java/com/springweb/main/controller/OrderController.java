package com.springweb.main.controller;

import com.springweb.main.cache.OrderStatusLocalCache;
import com.springweb.main.model.OrderDetail;
import com.springweb.main.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("cpuList", orderService.getCpuList());
        model.addAttribute("memoryList", orderService.getMemoryList());
        model.addAttribute("monitorList", orderService.getMonitorList());
        return "order";

    }

    @PostMapping("/order")
    public String order(String username, String address, String countSelectionInput) {


        OrderDetail orderDetail = orderService.generateOrderDetail(countSelectionInput, username, address);
        boolean result = orderService.reduceStock();
        orderDetail.setOrderResult(result);
        OrderStatusLocalCache.addOrderDetail(orderDetail);
        return "redirect:/status/" + orderDetail.getOrderId();

    }
}
