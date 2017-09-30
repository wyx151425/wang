package com.rumofuture.wang.controller;

import com.rumofuture.wang.model.domain.Member;
import com.rumofuture.wang.model.domain.Notification;
import com.rumofuture.wang.model.domain.User;
import com.rumofuture.wang.model.dto.Response;
import com.rumofuture.wang.model.dto.ResponseMember;
import com.rumofuture.wang.model.dto.ResponseMemberList;
import com.rumofuture.wang.service.MemberService;
import com.rumofuture.wang.service.NotificationService;
import com.rumofuture.wang.service.UserService;
import com.rumofuture.wang.util.DataVerificationUtil;
import com.rumofuture.wang.util.PromptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

@RestController
@RequestMapping("/member")
public class MemberController {

    private final UserService userService;
    private final MemberService memberService;
    private final NotificationService notificationService;

    @Autowired
    public MemberController(UserService userService, MemberService memberService, NotificationService notificationService) {
        this.userService = userService;
        this.memberService = memberService;
        this.notificationService = notificationService;
    }


    @PostMapping(value = "/invite")
    public Response saveMember(@RequestBody Member member) {
        if (DataVerificationUtil.isNullObject(member) || DataVerificationUtil.isNullObject(member.getLeader()) ||
                DataVerificationUtil.isNullObject(member.getLeader().getId()) ||
                DataVerificationUtil.isEmptyStringList(member.getName(), member.getMobilePhoneNumber(), member.getLeader().getName())) {
            return new Response(false, PromptUtil.DATA_TRANSMISSION_ERROR);
        }

        if (!DataVerificationUtil.isMobilePhoneNumber(member.getMobilePhoneNumber())) {
            return new Response(false, PromptUtil.USER_MOBILE_PHONE_NUMBER_FORMAT_ERROR);
        }

        User targetUser = userService.getUserByMobilePhoneNumber(member.getMobilePhoneNumber());
        if (DataVerificationUtil.isNullObject(targetUser)) {
            return new Response(false, PromptUtil.USER_NOT_EXIST);
        }

        List<Notification> notificationList = notificationService.getNotificationList(member.getLeader().getId(), targetUser.getId(), 2);
        if (null != notificationList && 0 != notificationList.size()) {
            return new Response(false, PromptUtil.INVITATION_ALREADY_EXIST);
        }

        String notificationContent = member.getLeader().getName() +
                "邀请您加入他的团队，担任" +
                member.getTeamPosition() +
                "职务。";
        Notification notification = new Notification();
        notification.setNotifier(member.getLeader());
        notification.setTarget(targetUser);
        notification.setChecked(false);
        notification.setType(2);
        notification.setContent(notificationContent);

        int id = notificationService.saveNotification(notification);

        if (0 == id) {
            return new Response(false, PromptUtil.INVITATION_SAVE_FAILED);
        }

        return new Response(true, PromptUtil.INVITATION_SAVE_SUCCESS);
    }

    @PostMapping(value = "delete")
    public ResponseMember deleteMember(@RequestParam("id") Integer id) {
        if (DataVerificationUtil.isNullObject(id)) {
            return new ResponseMember(false, PromptUtil.DATA_TRANSMISSION_ERROR, null);
        }

        int result = memberService.deleteMember(id);

        if (1 == result) {
            return new ResponseMember(true, PromptUtil.DELETE_SUCCESS, null);
        } else {
            return new ResponseMember(false, PromptUtil.DELETE_FAILED, null);
        }
    }

    @PostMapping(value = "/info/update")
    public ResponseMember updateMemberInfo(@RequestBody Member member) {
        if (DataVerificationUtil.isNullObject(member.getId())) {
            return new ResponseMember(false, PromptUtil.DATA_TRANSMISSION_ERROR, null);
        }

        Member resultMember = memberService.updateMemberInfo(member);
        if (DataVerificationUtil.isNullObject(resultMember)) {
            return new ResponseMember(false, PromptUtil.UPDATE_FAILED, null);
        }

        return new ResponseMember(true, PromptUtil.UPDATE_SUCCESS, resultMember);
    }

    @GetMapping(value = "/get")
    public ResponseMember getMember(@RequestParam("id") Integer id) {
        if (DataVerificationUtil.isNullObject(id)) {
            return new ResponseMember(false, PromptUtil.DATA_TRANSMISSION_ERROR, null);
        }

        Member member = memberService.getMemberById(id);
        return new ResponseMember(true, "message", member);
    }

    @GetMapping(value = "/list")
    public ResponseMemberList getMemberList(@RequestParam("id") Integer id) {
        if (DataVerificationUtil.isNullObject(id)) {
            return new ResponseMemberList(false, PromptUtil.DATA_TRANSMISSION_ERROR, null);
        }

        List<Member> memberList = memberService.getMemberListByLeader(id);
        return new ResponseMemberList(true, "message", memberList);
    }
}
