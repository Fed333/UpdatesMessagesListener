package org.fed333.example.sns.listener.controller.listener;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.extern.slf4j.Slf4j;
import org.fed333.example.sns.listener.service.NotificationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy.ON_SUCCESS;

@Slf4j
@Controller
public class UpdatesMessagesSqsConsumer {

    @Autowired
    private NotificationSenderService notificationSender;

    @Value("${sns.topic.updates-messages.arn}")
    private String updateMessagesSnsArn;

    @Autowired
    private AmazonSNS snsClient;

    @SqsListener(value = "${queue.updates-messages-url}", deletionPolicy = ON_SUCCESS)
    public void consume(String message) {
        log.info("consume message from UpdatesMessagesQueue.");
        log.info("message {}.", message);
        notificationSender.send(message, "Updates annoying info.");
    }

}
