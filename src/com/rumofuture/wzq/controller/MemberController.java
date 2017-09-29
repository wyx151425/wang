package com.rumofuture.wzq.controller;

import com.rumofuture.wzq.model.domain.Member;
import com.rumofuture.wzq.model.domain.User;
import com.rumofuture.wzq.model.dto.ResponseMember;
import com.rumofuture.wzq.model.dto.ResponseMemberList;
import com.rumofuture.wzq.model.dto.ResponseUser;
import com.rumofuture.wzq.service.MemberService;
import com.rumofuture.wzq.util.DataVerificationUtil;
import com.rumofuture.wzq.util.PromptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "/invite")
    public ResponseMember saveMember(@RequestBody Member member) {
        if (DataVerificationUtil.isNullObject(member) || DataVerificationUtil.isNullObject(member.getLeader().getId()) ||
                DataVerificationUtil.isEmptyStringList(member.getName(), member.getMobilePhoneNumber())) {
            return new ResponseMember(false, PromptUtil.DATA_TRANSMISSION_ERROR, null);
        }

        if (!DataVerificationUtil.isMobilePhoneNumber(member.getMobilePhoneNumber())) {
            return new ResponseMember(false, PromptUtil.USER_MOBILE_PHONE_NUMBER_FORMAT_ERROR, null);
        }

        Member validMember = memberService.getMemberByMobilePhoneNumber(member.getMobilePhoneNumber());
        if (!DataVerificationUtil.isNullObject(validMember)) {
            return new ResponseMember(false, PromptUtil.MEMBER_ALREADY_EXISTS, null);
        }

        Member resultMember = memberService.saveMember(member);

        if (DataVerificationUtil.isNullObject(resultMember)) {
            return new ResponseMember(false, PromptUtil.SAVE_FAILED, null);
        }

        return new ResponseMember(true, PromptUtil.SAVE_SUCCESS, resultMember);
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
