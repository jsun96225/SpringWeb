package com.springweb.main.service;

import com.springweb.main.entity.Component;
import com.springweb.main.entity.Cpu;
import com.springweb.main.entity.Memory;
import com.springweb.main.entity.Monitor;
import com.springweb.main.model.OrderDetail;
import com.springweb.main.service.component.BaseComponentService;
import com.springweb.main.service.component.CpuService;
import com.springweb.main.service.component.MenoryService;
import com.springweb.main.service.component.MonitorService;
import com.springweb.main.util.OrderSelectionInputHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.*;

@Service
public class OrderService {

    private CpuService cpuService;
    private MenoryService menoryService;
    private MonitorService monitorService;

    private Map<Long, Cpu> cpuMap = new HashMap<>();
    private Map<Long, Monitor> monitorMap = new HashMap<>();
    private Map<Long, Memory> memoryMap = new HashMap<>();
    private OrderSelectionInputHelper orderSelectionInputHelper;


    @Autowired
    public OrderService(CpuService cpuService, MenoryService menoryService, MonitorService monitorService) {
        this.cpuService = cpuService;
        this.menoryService = menoryService;
        this.monitorService = monitorService;
    }

    public List<Cpu> getCpuList() {
        List<Cpu> cpuList = cpuService.getAllComponents();
        for (Cpu cpu: cpuList) {
            cpuMap.put(cpu.getId(), cpu);
        }
        return cpuList;
    }


    public List<Memory> getMemoryList() {
        List<Memory> memoryList = menoryService.getAllComponents();
        for (Memory memory: memoryList) {
            memoryMap.put(memory.getId(), memory);
        }
        return memoryList;
    }


    public List<Monitor> getMonitorList() {
        List<Monitor> monitorList = monitorService.getAllComponents();
        for (Monitor monitor: monitorList) {
            monitorMap.put(monitor.getId(), monitor);
        }
        return monitorList;
    }

    public OrderDetail generateOrderDetail(String countSelectionInput, String username, String address) {
        orderSelectionInputHelper = new OrderSelectionInputHelper((countSelectionInput));
        String orderId = UUID.randomUUID().toString();
        OrderDetail orderDetail = new OrderDetail(orderId, username, address);
        orderDetail.setCpuCountMap(getComponentCount(cpuMap, "cpu"));
        orderDetail.setMoniterCountMap(getComponentCount(monitorMap, "monitor"));
        orderDetail.setMemoryCountMap(getComponentCount(memoryMap, "memory"));
        orderDetail.setTotalPrice();
        orderDetail.updateOrderItemList();
        return orderDetail;
    }

    private <T> Map<T, Integer> getComponentCount(Map<Long, T> listMap, String comonentName) {
        Map<Long, Integer> componentCpunts = orderSelectionInputHelper.getComponentOrders(comonentName);
        Map<T, Integer> countMap = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : componentCpunts.entrySet()) {
            countMap.put(listMap.get(entry.getKey()), entry.getValue());
        }

        return countMap;
     }

     public boolean reduceStock() {
       try {
           reduce(cpuService, orderSelectionInputHelper.getComponentOrders("cpu"));
           reduce(menoryService, orderSelectionInputHelper.getComponentOrders("memory"));
           reduce(monitorService, orderSelectionInputHelper.getComponentOrders("monitor"));
           return true;
       } catch (Exception exception) {
           return false;
       }
     }

     private <T extends Component> void reduce(BaseComponentService<T> componentService, Map<Long, Integer> componentOrders) {
        if (componentOrders != null && componentOrders.size() > 0) {
            for (Map.Entry<Long, Integer> componentOrder : componentOrders.entrySet()) {
                componentService.reduceStockById(componentOrder.getKey(), componentOrder.getValue());
            }
        }
     }
}
