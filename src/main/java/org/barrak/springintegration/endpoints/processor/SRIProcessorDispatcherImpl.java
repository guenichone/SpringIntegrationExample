package org.barrak.springintegration.endpoints.processor;

import org.barrak.springintegration.endpoints.common.Dispatcher;
import org.barrak.springintegration.model.SRIGenericRequest;
import org.barrak.springintegration.model.SRIGenericRequestHeaders;
import org.barrak.springintegration.model.SRIRequestType;
import org.barrak.springintegration.registry.ChannelRegistry;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Implementation of a custom Dispatcher for SRIGenericRequest.
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
@Component
public class SRIProcessorDispatcherImpl implements Dispatcher<SRIGenericRequest> {

    @Autowired
    private BeanFactory beanFactory;
    
    @Autowired
    @Qualifier(ProcessorQualifier.SRI_PROCESSOR_A)
    private SRIProcessor processorA;
    @Autowired
    @Qualifier(ProcessorQualifier.SRI_PROCESSOR_B)
    private SRIProcessor processorB;

    @ServiceActivator(
        inputChannel = ChannelRegistry.GLOBAL_MESSAGE_PROCESSOR,
        poller = @Poller(fixedRate = "5000"))
    @Override
    public void dispatchMessage(Message<SRIGenericRequest> request) {
        // Get the request type header.
        SRIRequestType requestType = (SRIRequestType) request.getHeaders().get(
                SRIGenericRequestHeaders.REQUEST_TYPE);
        
        // THIS IS NOT WORKING !!!!
//        SRIProcessor processor = BeanFactoryAnnotationUtils.qualifiedBeanOfType(
//                beanFactory, SRIProcessor.class, requestType.getProcessorQualifier());

        SRIProcessor processor = null;
        switch (requestType.getProcessorQualifier()) {
            case ProcessorQualifier.SRI_PROCESSOR_A:
                processor = processorA;
                break;
                
            case ProcessorQualifier.SRI_PROCESSOR_B:
                processor = processorB;
                break;
        }
        
        processor.processSRIRequestMessage(request);
    }
    
}
