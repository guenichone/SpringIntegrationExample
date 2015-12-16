package org.barrak.springintegration.model;

import org.barrak.springintegration.endpoints.processor.ProcessorQualifier;

/**
 * SRIRequestType used to map a request to a specific process.
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
public enum SRIRequestType {
    
    REQUEST_TYPE_A(ProcessorQualifier.SRI_PROCESSOR_A),
    REQUEST_TYPE_B(ProcessorQualifier.SRI_PROCESSOR_B);
    
    private final String processorQualifier;

    private SRIRequestType(String processorQualifier) {
        this.processorQualifier = processorQualifier;
    }

    public String getProcessorQualifier() {
        return processorQualifier;
    }
}
