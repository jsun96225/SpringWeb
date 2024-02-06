package com.springweb.main.repositories;

import com.springweb.main.entity.Memory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MemoryRepository extends BaseJpaRepository<Memory>{

    @Modifying
    @Transactional
    @Query(value = "update t_memory set stock = stock - ?2 where id = ?1", nativeQuery = true)
    void reduceStock(long id, int count);

    @Modifying
    @Transactional
    @Query(value = "update t_memory set stock = stock + ?2 where id = ?1", nativeQuery = true)
    void restoreStock(long id, int count);
}
