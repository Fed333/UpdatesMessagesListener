package org.fed333.example.sns.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(
    exclude = {
            org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
            org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
            org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
    }
)
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }

}
