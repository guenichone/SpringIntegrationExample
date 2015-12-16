package org.barrak.springintegration.endpoints.connector.rest;

import org.barrak.springintegration.channels.GlobalValidatorChannel;
import org.barrak.springintegration.model.SRIGenericRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTEndpoint used to get REST call on the local embedded tomcat.
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
@RestController
public class RESTEndpointImpl implements RESTEndpoint {
    
    @Autowired
    private GlobalValidatorChannel validatorChannel;

    /**
     * Simple request entry point to map a parameter id to a new message using SRIGenericRequest as payload.
     * The message creation and send are done manually here.
     * 
     * @param id The id to create a new SRIGenericRequest
     * @return "Registering " + id + "...".
     */
    @RequestMapping("/hello1/{id}")
    @Override
    public String hello1(@PathVariable String id) {
        
        System.out.println("Hello 1 ... " + id);
        
        SRIGenericRequest genericRequest = new SRIGenericRequest(id);
        
        Message<SRIGenericRequest> response =
            MessageBuilder.withPayload(genericRequest)
                .setHeader("ACCOUNT_EXPIRY", "NEVER")
                .build();
        
        validatorChannel.send(response);
        
        return "Registering " + id + "...";
    }
    
    /**
     * Simple request entry point to map a parameter id to a new message using SRIGenericRequest as payload.
     * The message creation and payload binding are done automatically by annotation and a transformer.
     * See : Application.java
     * 
     * @param id The id to create a new SRIGenericRequest
     * @return "Registering " + id + "...".
     */
    @RequestMapping("/hello2/{id}")
    @Override
    public String hello2(@Payload @PathVariable String id) {
        System.out.println("Hello 2 ... " + id);
        
        return "Registering " + id + "...";
    }
}
