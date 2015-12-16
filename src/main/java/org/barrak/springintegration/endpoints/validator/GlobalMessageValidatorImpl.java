package org.barrak.springintegration.endpoints.validator;

import org.barrak.springintegration.model.SRIGenericRequest;
import org.barrak.springintegration.model.SRIGenericRequestHeaders;
import org.barrak.springintegration.model.SRIRequestType;
import org.barrak.springintegration.registry.ChannelRegistry;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * GlobalMessageValidatorImpl used to make the global validation of the message details from other systems.
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
@MessageEndpoint
public class GlobalMessageValidatorImpl implements GlobalMessageValidator {

    @ServiceActivator(
            inputChannel = ChannelRegistry.GLOBAL_MESSAGE_VALIDATOR,
            outputChannel = ChannelRegistry.GLOBAL_MESSAGE_PROCESSOR,
            poller = @Poller(fixedRate = "5000"))
    @Override
    public Message<SRIGenericRequest> validateSRIGenericRequest(Message<SRIGenericRequest> message) {

        // Generate a random request type to simulate any kind of validation and header modification
        boolean requestTypeA = Math.random() * 2 >= 1;

        // Do some validation stuff.
        System.out.println("I'm doing some validation stuff for " + message.getPayload().getId() + " ... I will answer " + (requestTypeA ? "A" : "B"));

        // Build the response
        SRIRequestType requestType = requestTypeA
                ? SRIRequestType.REQUEST_TYPE_A
                : SRIRequestType.REQUEST_TYPE_B;

        // Add header
        return MessageBuilder.fromMessage(message).setHeader(SRIGenericRequestHeaders.REQUEST_TYPE, requestType).build();
    }
}
