package com.utility.notificationService;

import lombok.Data;

@Data
public class NotificationRequestDto {

    private String toEmail;
    private String orderId;
    private String templateName;
    private String subject;

    private String mobile;
    private String countryCode;
}
