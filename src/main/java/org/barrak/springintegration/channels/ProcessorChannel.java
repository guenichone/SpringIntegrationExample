package org.barrak.springintegration.channels;

import org.barrak.springintegration.registry.ChannelRegistry;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
@Component(ChannelRegistry.GLOBAL_MESSAGE_PROCESSOR)
public class ProcessorChannel extends QueueChannel {

    @Override
    protected Message<?> doReceive(long timeout) {
        return super.doReceive(timeout);
    }

    @Override
    protected boolean doSend(Message<?> message, long timeout) {
        System.out.println("Queue Processor send : " + message);
        return super.doSend(message, timeout);
    }
    
}
