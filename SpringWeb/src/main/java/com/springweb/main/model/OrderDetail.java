package com.springweb.main.model;

import com.springweb.main.entity.Component;
import com.springweb.main.entity.Cpu;
import com.springweb.main.entity.Memory;
import com.springweb.main.entity.Monitor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDetail {

    private String orderId;
    private String username;
    private String address;

    private Map<Cpu, Integer> cpuCountMap;
    private Map<Memory, Integer> memoryCountMap;
    private Map<Monitor, Integer> moniterCountMap;

    private final List<OrderItem> orderItemList = new ArrayList<>();
    private double totalPrice = 0;
    private boolean orderResult;

    public OrderDetail(String orderId, String username, String address) {
        this.orderId = orderId;
        this.username = username;
        this.address = address;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<Cpu, Integer> getCpuCountMap() {
        return cpuCountMap;
    }

    public void setCpuCountMap(Map<Cpu, Integer> cpuCountMap) {
        this.cpuCountMap = cpuCountMap;
    }

    public Map<Memory, Integer> getMemoryCountMap() {
        return memoryCountMap;
    }

    public void setMemoryCountMap(Map<Memory, Integer> memoryCountMap) {
        this.memoryCountMap = memoryCountMap;
    }

    public Map<Monitor, Integer> getMoniterCountMap() {
        return moniterCountMap;
    }

    public void setMoniterCountMap(Map<Monitor, Integer> moniterCountMap) {
        this.moniterCountMap = moniterCountMap;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void updateOrderItemList() {
        orderItemList.addAll(getComponentItems(cpuCountMap));
        orderItemList.addAll(getComponentItems(memoryCountMap));
        orderItemList.addAll(getComponentItems(moniterCountMap));
    }

    private <T extends Component> List<OrderItem> getComponentItems(Map<T, Integer> countMap) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Map.Entry<T, Integer> entry: countMap.entrySet()) {
            T component = entry.getKey();
            String type = component.getClass().getSimpleName();
            orderItems.add(new OrderItem(type, component.getName(), component.getPrice(), entry.getValue()));
        }
        return orderItems;

    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        totalPrice += countComponentTotalPrice(cpuCountMap);
        totalPrice += countComponentTotalPrice(memoryCountMap);
        totalPrice += countComponentTotalPrice(moniterCountMap);
    }

    private <T extends Component> double countComponentTotalPrice(Map<T, Integer> countMap) {
        double amount = 0;
        for (Map.Entry<T, Integer> entry : countMap.entrySet()) {
            amount += entry.getKey().getPrice() * entry.getValue();
        }
        return amount;
    }

    public boolean isOrderResult() {
        return orderResult;
    }

    public void setOrderResult(boolean orderResult) {
        this.orderResult = orderResult;
    }
}
