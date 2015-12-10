package org.barrak.springintegration.test.endpoints;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.barrak.springintegration.Application;
import org.barrak.springintegration.endpoints.input.jms.JMSRegistryName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Emilien Guenichon <emilien.guenichon@cgi.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class JMSEndpointTest {
    
    @Autowired
    private ApplicationContext ctx;
    
    @Test
    public void testJMSEndpoint() {
        // Send a message
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("12345");
            }
        };
        JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
        System.out.println("Sending a new message.");
        jmsTemplate.send(JMSRegistryName.JMS_MAIN, messageCreator);
        
        
    }
    
}
