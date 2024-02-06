package com.springweb.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
@NoRepositoryBean
public interface BaseJpaRepository<T> extends JpaRepository<T, Long> {

    T findByName(String name);

    @Override
    Optional<T> findById(Long aLong);

    void reduceStock(long id, int count);

    void restoreStock(long id, int count);
}
