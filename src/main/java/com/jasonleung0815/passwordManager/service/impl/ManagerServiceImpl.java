package com.jasonleung0815.passwordManager.service.impl;

import com.jasonleung0815.passwordManager.dao.ManagerRepository;
import com.jasonleung0815.passwordManager.model.Manager;
import com.jasonleung0815.passwordManager.service.IManagerService;
import com.jasonleung0815.passwordManager.utils.AESUtils;
import org.apache.commons.lang.RandomStringUtils;
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

    @Value("${manager.saltkey}")
    private String saltkey;

    @Override
    public Manager addAccount(Manager addForm) throws Exception {
        Manager manager = new Manager();
        manager.setAccount(addForm.getAccount());
        manager.setDomain(addForm.getDomain());
        //TODO: Think how to add salt to the password and hard to crack
        String salt = RandomStringUtils.randomAlphabetic(16);
        manager.setPassword(AESUtils.Encrypt(addForm.getPassword(), appkey, salt));
        managerRepository.save(manager);
        return manager;
    }

    @Override
    public Manager getAccount(Long id) throws Exception {
        Manager returnManager;
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
            returnManager = manager.get();
            String wSalt = AESUtils.Decrypt(returnManager.getPassword(), appkey, null);
            //take last 6 char
            if (wSalt != null) {
                String salt = wSalt.substring(wSalt.length() - 6);
                returnManager.setPassword("123");
                return returnManager;
            }
            return null;
        } else {
            return null;
        }
    }

    @Override
    public Manager delAccount(Long id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
            managerRepository.deleteById(manager.get().getId());
            return manager.get();
        } else {
           return null;
        }
    }

}
