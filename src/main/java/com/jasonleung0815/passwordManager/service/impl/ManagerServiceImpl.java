package com.jasonleung0815.passwordManager.service.impl;

import com.jasonleung0815.passwordManager.dao.ManagerRepository;
import com.jasonleung0815.passwordManager.model.Manager;
import com.jasonleung0815.passwordManager.service.IManagerService;
import com.jasonleung0815.passwordManager.utils.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerServiceImpl implements IManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Value("${manager.appkey}")
    private String appkey;

    @Override
    public Manager addAccount(Manager addForm) throws Exception {
        Manager manager = new Manager();
        manager.setAccount(addForm.getAccount());
        manager.setDomain(addForm.getDomain());
        manager.setPassword(AESUtils.Encrypt(addForm.getPassword(), appkey));
        managerRepository.save(manager);
        return manager;
    }

    @Override
    public Manager getAccount(Long id) throws Exception {
        Manager returnManager = new Manager();
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
            returnManager = manager.get();
            String dsrc = AESUtils.Decrypt(returnManager.getPassword(), appkey);
            returnManager.setPassword(dsrc);
            return returnManager;
        } else {
            throw new RuntimeException("No account found");
        }
    }

}
