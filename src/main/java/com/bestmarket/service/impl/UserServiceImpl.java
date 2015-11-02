package com.bestmarket.service.impl;

import com.bestmarket.entity.User;
import com.bestmarket.service.UserService;
import com.bestmarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation= Propagation.REQUIRED, readOnly=true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public User saveAnfFlush(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findByMail(String mail) {
        return userRepository.findByEmail(mail);
    }
}
