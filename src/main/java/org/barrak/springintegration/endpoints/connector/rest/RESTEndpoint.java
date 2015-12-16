package org.barrak.springintegration.endpoints.connector.rest;

import org.barrak.springintegration.registry.ChannelRegistry;
import org.springframework.integration.annotation.Gateway;

/**
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
public interface RESTEndpoint {

    String hello1(String id);
    
    @Gateway(requestChannel=ChannelRegistry.GLOBAL_MESSAGE_VALIDATOR)
    String hello2(String id);
    
}
