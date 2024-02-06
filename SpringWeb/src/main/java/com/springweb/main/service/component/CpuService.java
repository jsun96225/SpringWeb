package com.springweb.main.service.component;

import com.springweb.main.entity.Cpu;
import com.springweb.main.repositories.CpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpuService extends BaseComponentService<Cpu> {

    @Autowired
    public CpuService(CpuRepository cpuRepository) {
        super(cpuRepository);
    }
}
