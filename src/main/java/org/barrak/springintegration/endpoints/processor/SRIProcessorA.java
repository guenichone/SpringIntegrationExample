package org.barrak.springintegration.endpoints.processor;

import org.barrak.springintegration.model.SRIGenericRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
@Component
@Qualifier(ProcessorQualifier.SRI_PROCESSOR_A)
public class SRIProcessorA implements SRIProcessor {

    @Override
    public void processSRIRequestMessage(Message<SRIGenericRequest> request) {
        System.out.println("Processing A " + request.getPayload().getId() + "...");
    }
    
}
