package org.barrak.springintegration.endpoints.input.jms;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.util.FileSystemUtils;

/**
 * JMSEndpoint on jmq-main-queue
 *
 * @author Emilien Guenichon <emilien.guenichon@cgi.com>
 */
@MessageEndpoint
public class JMSEndpoint {

    @Autowired
    private ConfigurableApplicationContext context;
    
    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     */
    @JmsListener(destination = JMSRegistryName.JMS_MAIN)
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        
        
    }
}