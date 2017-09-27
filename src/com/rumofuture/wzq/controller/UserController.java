package com.rumofuture.wzq.controller;

import com.rumofuture.wzq.model.domain.User;
import com.rumofuture.wzq.model.dto.ResponseUser;
import com.rumofuture.wzq.service.UserService;
import com.rumofuture.wzq.util.DataVerificationUtil;
import com.rumofuture.wzq.util.PromptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signUp")
    public ResponseUser signUp(@RequestBody User user) {

        if (DataVerificationUtil.isNullObject(user) ||
                DataVerificationUtil.isEmptyStringList(user.getName(), user.getMobilePhoneNumber(), user.getPassword())) {
            return new ResponseUser(false, PromptUtil.DATA_TRANSMISSION_ERROR, null);
        }

        if (!DataVerificationUtil.isMobilePhoneNumber(user.getMobilePhoneNumber())) {
            return new ResponseUser(false, PromptUtil.USER_MOBILE_PHONE_NUMBER_FORMAT_ERROR, null);
        }

        if (!DataVerificationUtil.isPassword(user.getPassword())) {
            return new ResponseUser(false, PromptUtil.USER_PASSWORD_FORMAT_ERROR, null);
        }

        User validUser = userService.getUserByMobilePhoneNumber(user.getMobilePhoneNumber());
        if (!DataVerificationUtil.isNullObject(validUser)) {
            return new ResponseUser(false, PromptUtil.SIGNED_USER, null);
        }

        User resultUser = userService.signUp(user);

        if (DataVerificationUtil.isNullObject(resultUser)) {
            return new ResponseUser(false, PromptUtil.SIGN_UP_FAILED, null);
        } else {
            return new ResponseUser(true, PromptUtil.SIGN_UP_SUCCESS, resultUser);
        }
    }

    @PostMapping(value = "/login")
    public ResponseUser logIn(@RequestBody User user) {

        if (DataVerificationUtil.isNullObject(user) ||
                DataVerificationUtil.isEmptyStringList(user.getMobilePhoneNumber(), user.getPassword())) {
            return new ResponseUser(false, PromptUtil.DATA_TRANSMISSION_ERROR, null);
        }

        if (!DataVerificationUtil.isMobilePhoneNumber(user.getMobilePhoneNumber())) {
            return new ResponseUser(false, PromptUtil.USER_MOBILE_PHONE_NUMBER_FORMAT_ERROR, null);
        }

        if (!DataVerificationUtil.isPassword(user.getPassword())) {
            return new ResponseUser(false, PromptUtil.USER_PASSWORD_FORMAT_ERROR, null);
        }

        User resultUser = userService.logIn(user.getMobilePhoneNumber());

        if (DataVerificationUtil.isNullObject(resultUser)) {
            return new ResponseUser(false, PromptUtil.UNSIGNED_USER, null);
        } else {
            if (resultUser.getPassword().equals(user.getPassword())) {
                return new ResponseUser(true, PromptUtil.LOG_IN_SUCCESS, resultUser);
            } else {
                return new ResponseUser(false, PromptUtil.USER_PASSWORD_ERROR, null);
            }
        }
    }

    @PostMapping(value = "/password/update")
    public ResponseUser updateUserPassword(
            @RequestParam("id") Integer id,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword
    ) {
        if (DataVerificationUtil.isNullObject(id) ||
                DataVerificationUtil.isEmptyStringList(oldPassword, newPassword)) {
            return new ResponseUser(false, PromptUtil.DATA_TRANSMISSION_ERROR, null);
        }

        if (DataVerificationUtil.isPassword(newPassword)) {
            return new ResponseUser(false, PromptUtil.USER_PASSWORD_FORMAT_ERROR, null);
        }

        User targetUser = userService.getUserById(id);
        if (null != targetUser) {
            if (targetUser.getPassword().equals(oldPassword)) {
                targetUser.setPassword(newPassword);
                User resultUser = userService.updateUserPassword(targetUser);
                if (null == resultUser) {
                    return new ResponseUser(false, PromptUtil.UPDATE_FAILED, null);
                } else {
                    return new ResponseUser(true, PromptUtil.UPDATE_SUCCESS, resultUser);
                }
            } else {
                return new ResponseUser(false, PromptUtil.USER_PASSWORD_ERROR, null);
            }
        }

        return new ResponseUser(false, PromptUtil.UPDATE_FAILED, null);
    }

    @PostMapping(value = "/info/update")
    public ResponseUser updateUserInfo(@RequestBody User user) {
        if (DataVerificationUtil.isNullObject(user.getId())) {
            return new ResponseUser(false, PromptUtil.DATA_TRANSMISSION_ERROR, null);
        }
        User resultUser = userService.updateUserInfo(user);
        if (DataVerificationUtil.isNullObject(resultUser)) {
            return new ResponseUser(false, PromptUtil.UPDATE_FAILED, null);
        }
        return new ResponseUser(true, PromptUtil.UPDATE_SUCCESS, resultUser);
    }
}