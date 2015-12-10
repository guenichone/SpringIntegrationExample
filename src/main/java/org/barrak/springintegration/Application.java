package org.barrak.springintegration;

import org.barrak.springintegration.model.SRIGenericRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.messaging.Message;

@Configuration
@SpringBootApplication
@IntegrationComponentScan
@EnableJms
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        SRIGenericRequest r = new SRIGenericRequest("12345");
        r.addAttribute("attr1", "1");
        
        Message<SRIGenericRequest> m =
            MessageBuilder.withPayload(r)
                .setHeader("ACCOUNT_EXPIRY", "NEVER")
                .build();
        
        // Converter
//        TempConverter converter = ctx.getBean(TempConverter.class);
//        System.out.println(converter.fahrenheitToCelcius(68.0f));
//        ctx.close();
    }

//    @MessagingGateway
    public interface TempConverter {
//        @Gateway(requestChannel = "convert.input")
        float fahrenheitToCelcius(float fahren);
    }
}
