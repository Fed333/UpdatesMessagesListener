package org.fed333.example.sns.listener.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NotificationSenderService {

    @Value("${sns.topic.updates-messages.arn}")
    private String updateMessagesSnsArn;

    @Autowired
    private AmazonSNS snsClient;

    @Autowired
    private NotificationMessagingTemplate messagingTemplate;

    public void send(String message, String subject) {
        String sendDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        message = "[" + sendDateTime + "] " + message;
        PublishRequest publishRequest = new PublishRequest(updateMessagesSnsArn, message, subject);
        snsClient.publish(publishRequest);

    }

}
