package com.bestmarket.service;

import com.bestmarket.entity.User;

import java.util.List;

public interface UserService {
    User saveAnfFlush(User user);
    User findByMail(String mail);
}
