package com.jasonleung0815.passwordManager.dao;

import com.jasonleung0815.passwordManager.model.Spending;
import org.springframework.data.repository.CrudRepository;

public interface SpendingRepository extends CrudRepository<Spending, Long> {
}
