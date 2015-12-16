package org.barrak.springintegration.endpoints.connector.jms;

/**
 *
 * @author eguenichon
 */
public interface JMSInputEndpoint {
    
    void receiveMessage(String message);
}
