package com.springweb.main.service;

import com.springweb.main.entity.Cpu;
import com.springweb.main.entity.Memory;
import com.springweb.main.entity.Monitor;
import com.springweb.main.service.component.CpuService;
import com.springweb.main.service.component.MenoryService;
import com.springweb.main.service.component.MonitorService;
import com.springweb.main.util.CsvLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoaderService {
    private CpuService cpuService;
    private MenoryService menoryService;
    private MonitorService monitorService;
    private CsvLoader csvLoader;


    @Autowired
    public LoaderService(CpuService cpuService, MenoryService menoryService, MonitorService monitorService) {
        this.cpuService = cpuService;
        this.menoryService = menoryService;
        this.monitorService = monitorService;
    }

    public boolean loadAllFromFiles(String folder) {
        csvLoader = new CsvLoader(folder);
        try {
            loadCpuList();
            loadMemoryList();
            loadMonitorList();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

    }

    private void loadCpuList() {
        for (Cpu cpu: csvLoader.loadCpuList()) {
            cpuService.saveComponent(cpu);
        }
    }

    private void loadMonitorList() {
        for (Monitor monitor: csvLoader.loadMonitorList()) {
            monitorService.saveComponent(monitor);
        }
    }

    private  void loadMemoryList() {
        for (Memory memory: csvLoader.loadMemoryList()) {
            menoryService.saveComponent(memory);
        }
    }
}
