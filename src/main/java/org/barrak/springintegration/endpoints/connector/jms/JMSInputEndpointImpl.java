package org.barrak.springintegration.endpoints.connector.jms;

import org.barrak.springintegration.registry.JMSRegistry;
import org.barrak.springintegration.channels.GlobalValidatorChannel;
import org.barrak.springintegration.model.SRIGenericRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;

/**
 * JMSEndpoint on jmq-main-queue
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
@MessageEndpoint
public class JMSInputEndpointImpl implements JMSInputEndpoint {

    @Autowired
    private GlobalValidatorChannel validatorChannel;

    @Override
    @JmsListener(destination = JMSRegistry.JMS_MAIN)
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");

        SRIGenericRequest genericRequest = new SRIGenericRequest(message);
        
        Message<SRIGenericRequest> response =
            MessageBuilder.withPayload(genericRequest)
                .setHeader("ACCOUNT_EXPIRY", "NEVER")
                .build();
        
        validatorChannel.send(response);
    }
}
