package com.users.factory;

import com.utility.notificationService.Notification;
import com.utility.notificationService.SmsNotification;
import com.utility.notificationService.EmailNotification;
import com.utility.notificationService.NotificationRequestDto;

public class NotificationBeanConfig {

    private NotificationBeanConfig() {
    }

    public static Notification getNotificationBean(String value, NotificationRequestDto notificationRequestDto) {

        switch (value) {
            case "SMS":
                return new SmsNotification(notificationRequestDto);
            case "Email":
                return new EmailNotification(notificationRequestDto);
        }
        return null;
    }
}
