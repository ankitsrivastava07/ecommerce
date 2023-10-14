package com.utility.notificationService;

public class EmailNotification implements Notification {

    private NotificationRequestDto notificationRequestDto;

    public EmailNotification(NotificationRequestDto notificationRequestDto) {
        this.notificationRequestDto = notificationRequestDto;
    }

    @Override
    public void notification(NotificationRequestDto notificationRequestDto) {

    }
}
