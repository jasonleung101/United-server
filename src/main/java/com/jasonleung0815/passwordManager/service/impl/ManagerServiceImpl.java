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

    @Override
    public Manager addAccount(Manager addForm) throws Exception {
        Manager manager = new Manager();
        manager.setAccount(addForm.getAccount());
        manager.setDomain(addForm.getDomain());
        //Solution
        //let salt = "1234567812345678"
        //let pwd = "Testing123"
        //fullPwd = "12345678test12345678ing123"
        manager.setPassword(AESUtils.Encrypt(setPassword(addForm.getPassword()), appkey, null));
        managerRepository.save(manager);
        return manager;
    }

    @Override
    public Manager changeAccount(Manager changeForm) throws Exception {
        Manager returnManager;
        Optional<Manager> optionalManager = managerRepository.findById(changeForm.getId());
        if (optionalManager.isPresent()) {
            returnManager = optionalManager.get();
            returnManager.setAccount(changeForm.getAccount());
            returnManager.setDomain(changeForm.getDomain());
            returnManager.setPassword(AESUtils.Encrypt(setPassword(changeForm.getPassword()), appkey, null));
            managerRepository.save(returnManager);
            return returnManager;
        }
        return null;
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
                int length = Integer.parseInt(wSalt.substring(wSalt.length()-2));
                String removeFirstSalt = wSalt.substring(8);
                String firstPwd = removeFirstSalt.substring(0, removeFirstSalt.length()-(removeFirstSalt.length()-(length-6)));
                String secondPwd = wSalt.substring(0, wSalt.length()-2).substring((wSalt.length()-(length-firstPwd.length())-2));
                returnManager.setPassword(firstPwd+secondPwd);
                return returnManager;
            }
        }
        return null;
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

    private String setPassword(String pwd) {
        String salt = RandomStringUtils.randomAlphabetic(16);
        String firstSalt = salt.substring(0, salt.length()-8);
        String endSalt = salt.substring(salt.length()-8);
        String firstPwd = pwd.substring(0, pwd.length()-6);
        String endPwd = pwd.substring(firstPwd.length());
        int length = pwd.length();
        String fullPwd;
        if (length<10) {
            fullPwd = firstSalt + firstPwd + endSalt + endPwd + 0 + length;
        } else {
            fullPwd = firstSalt + firstPwd + endSalt + endPwd + length;
        }
        return fullPwd;
    }

}
