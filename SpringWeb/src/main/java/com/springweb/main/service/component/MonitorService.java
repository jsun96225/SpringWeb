package com.springweb.main.service.component;

import com.springweb.main.entity.Monitor;
import com.springweb.main.repositories.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorService extends BaseComponentService<Monitor> {
    @Autowired
    public MonitorService(MonitorRepository monitorRepository) {
        super(monitorRepository);
    }
}
