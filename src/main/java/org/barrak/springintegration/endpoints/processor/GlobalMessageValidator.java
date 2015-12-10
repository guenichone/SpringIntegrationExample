package org.barrak.springintegration.endpoints.processor;

import org.barrak.springintegration.model.SRIGenericRequest;
import org.barrak.springintegration.model.SRIRequestType;
import org.barrak.springintegration.model.SRISpecificRequest;
import org.barrak.springintegration.registry.ChannelRegistry;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 *
 * @author Emilien Guenichon <emilien.guenichon@cgi.com>
 */
@MessageEndpoint
public class GlobalMessageValidator {

    @ServiceActivator(
            inputChannel = ChannelRegistry.GLOBAL_MESSAGE_VALIDATOR, 
            outputChannel = ChannelRegistry.GLOBAL_MESSAGE_PROCESSOR)
    public Message<SRISpecificRequest> validateSRIGenericRequest(Message<SRIGenericRequest> message) {
        
        final boolean requestTypeA = Math.random() * 2 >= 1;
        
        // Do some validation stuff.
        System.out.println("I'm doing some validation stuff ... I will answer " + (requestTypeA ? "A" : "B"));
        
        // Build the response
        SRISpecificRequest responsePayload = () -> requestTypeA 
                ? SRIRequestType.REQUEST_TYPE_A
                : SRIRequestType.REQUEST_TYPE_B;
        
        return MessageBuilder.withPayload(responsePayload)
                .setHeader("ANOTHER_HEADER", "MY_HEADER")
                .build();
    }
}
