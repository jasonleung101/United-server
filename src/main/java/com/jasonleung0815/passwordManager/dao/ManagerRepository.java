package com.jasonleung0815.passwordManager.dao;

import com.jasonleung0815.passwordManager.model.Manager;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends CrudRepository<Manager, Long> {

    Optional<Manager> findById(Long id);

    List<Manager> findAll();

}
