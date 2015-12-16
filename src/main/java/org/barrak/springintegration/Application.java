package org.barrak.springintegration;

import org.barrak.springintegration.channels.GlobalValidatorChannel;
import org.barrak.springintegration.model.SRIGenericRequest;
import org.barrak.springintegration.registry.ChannelRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.config.EnablePublisher;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.messaging.Message;
import org.springframework.ws.config.annotation.EnableWs;

@SpringBootApplication
@EnableJms
@EnablePublisher(value = ChannelRegistry.DEFAULT_CHANNEL)
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        SRIGenericRequest r = new SRIGenericRequest("12345");
        r.addAttribute("attr1", "1");

        Message<SRIGenericRequest> m
                = MessageBuilder.withPayload(r)
                .setHeader("ACCOUNT_EXPIRY", "NEVER")
                .build();

        GlobalValidatorChannel defaultChannel = ctx.getBean(GlobalValidatorChannel.class);
        defaultChannel.send(m);
    }
    
    /**
     * Allow the mapping of a @Payload String to SRIGenericRequest
     * @param id The id to set.
     * @return The SRIGenericRequest object to get.
     */
    @Transformer
    public SRIGenericRequest createSRIGenericRequestFromId(String id) {
        System.out.println("Transform ... " + id);
        
        return new SRIGenericRequest(id);
    }
}
