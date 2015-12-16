package org.barrak.springintegration.channels;

import org.barrak.springintegration.registry.ChannelRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.stereotype.Component;

/**
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
@Component
@Qualifier(value = ChannelRegistry.DEFAULT_CHANNEL)
public class DefaultChannel extends QueueChannel {
    
}
