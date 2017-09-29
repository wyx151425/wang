package com.rumofuture.wzq.service;

import com.rumofuture.wzq.model.domain.Notification;

public interface NotificationService {
    Notification saveInvitation(Notification notification);
    int deleteInvitation(Integer id);
    Notification getInvitation(Integer inviterId, Integer inviteeId);
}
