package org.barrak.springintegration.endpoints.processor;

import org.barrak.springintegration.model.SRIGenericRequest;
import org.springframework.messaging.Message;

/**
 * Common interface for every SRIProcessor.
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
public interface SRIProcessor {
    
    /**
     * Process the SRIRequestMessage on a specific implementation.
     * @param request The message request.
     */
    void processSRIRequestMessage(Message<SRIGenericRequest> request);
    
}
