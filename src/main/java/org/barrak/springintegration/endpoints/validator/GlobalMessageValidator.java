package org.barrak.springintegration.endpoints.validator;

import org.barrak.springintegration.model.SRIGenericRequest;
import org.springframework.messaging.Message;

/**
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
public interface GlobalMessageValidator {
    
    Message<SRIGenericRequest> validateSRIGenericRequest(Message<SRIGenericRequest> message);
    
}
