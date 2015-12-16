package org.barrak.springintegration.endpoints.common;

import org.springframework.messaging.Message;

/**
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
public interface Dispatcher<T> {

    void dispatchMessage(Message<T> request);
    
}
