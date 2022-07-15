package org.fed333.example.sns.listener.controller;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.SubscribeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Subscribe2SnsTopicController {

    private static final String EMAIL_PROTOCOL = "email";

    @Value("${sns.topic.updates-messages.arn}")
    private String updateMessagesSnsArn;

    @Autowired
    private AmazonSNS client;

    @RequestMapping("/subscribe/email-to/sns")
    public String subscribe(@RequestParam(name = "email") String email){
      log.info("subscribe email '{}' to the topic {}", email, updateMessagesSnsArn);
      SubscribeRequest subscribeRequest = new SubscribeRequest(
              updateMessagesSnsArn,
              EMAIL_PROTOCOL,
              email);
      client.subscribe(subscribeRequest);
      return String.format("Your subscription on email %s has been successfully sent. Check out your email box.", email);
    }

}
