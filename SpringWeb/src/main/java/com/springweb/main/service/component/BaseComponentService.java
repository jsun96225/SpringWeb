package com.springweb.main.service.component;

import com.springweb.main.repositories.BaseJpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseComponentService<T> {

    private BaseJpaRepository<T> baseJpaRepository;

    protected BaseComponentService(BaseJpaRepository<T> baseJpaRepository) {
        this.baseJpaRepository = baseJpaRepository;
    }

    public List<T> getAllComponents() {
        return baseJpaRepository.findAll();
    }

    public void saveComponent(T component) {
        baseJpaRepository.save(component);
    }

    public void restoreStockById(long id, int count) {
        if (canUpdaeStock(id, count)) {
            baseJpaRepository.restoreStock(id, count);
        }
    }

    public boolean reduceStockById(long id, int count) {
        if (canUpdaeStock(id, count)) {
            baseJpaRepository.reduceStock(id, count);
            return true;
        }
        return false;
    }

    private boolean canUpdaeStock(long id, int count) {
        if (count <= 0) {
            return false;
        }
        Optional<T> component = baseJpaRepository.findById(id);
        return component.isPresent();
    }
}
