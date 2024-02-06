package com.springweb.main.service.component;

import com.springweb.main.entity.Memory;
import com.springweb.main.repositories.MemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenoryService extends BaseComponentService<Memory> {
    @Autowired
    public MenoryService(MemoryRepository memoryRepository) {
        super(memoryRepository);
    }
}
