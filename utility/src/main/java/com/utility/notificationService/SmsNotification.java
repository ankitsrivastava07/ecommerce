package com.utility.notificationService;

public class SmsNotification implements Notification {
    private NotificationRequestDto notificationRequestDto;

    public SmsNotification(NotificationRequestDto notificationRequestDto) {
        this.notificationRequestDto = notificationRequestDto;
    }

    @Override
    public void notification(NotificationRequestDto notificationRequestDto) {

    }
}
