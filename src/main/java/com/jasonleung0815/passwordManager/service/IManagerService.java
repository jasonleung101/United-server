package com.jasonleung0815.passwordManager.service;

import com.jasonleung0815.passwordManager.model.Manager;

public interface IManagerService {

    Manager addAccount(Manager manager) throws Exception;

    Manager getAccount(Long id) throws Exception;

    Manager delAccount(Long id);

}
