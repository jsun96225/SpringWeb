package com.springweb.main.repositories;

import com.springweb.main.entity.Cpu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CpuRepository extends BaseJpaRepository<Cpu> {
    @Modifying
    @Transactional
    @Query(value = "update t_cpu set stock = stock - ?2 where id = ?1", nativeQuery = true)
    void reduceStock(long id, int count);

    @Modifying
    @Transactional
    @Query(value = "update t_cpu set stock = stock + ?2 where id = ?1", nativeQuery = true)
    void restoreStock(long id, int count);
}
