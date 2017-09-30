package com.rumofuture.wang.service;

import com.rumofuture.wang.model.domain.User;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

public interface UserService {
    User signUp(User user);
    User logIn(String mobilePhoneNumber);
    User updateUserPassword(User user);
    User updateUserInfo(User user);
    User getUserById(Integer id);
    User getUserByMobilePhoneNumber(String mobilePhoneNumber);
}
