package org.barrak.springintegration.endpoints.connector.soap;

import org.barrak.springintegration.webservice.esbdt.schemas.RequestSRIServiceRequest;
import org.barrak.springintegration.webservice.esbdt.schemas.RequestSRIServiceResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
@Endpoint
public class SOAPEndpoint {

    private static final String NAMESPACE_URI = "http://webservice.springintegration.barrak.org/esbdt/schemas";

    // localPart is the name of the method request in the XSD
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "requestSRIServiceRequest")
    @ResponsePayload
    public RequestSRIServiceResponse requestSRIService(@RequestPayload RequestSRIServiceRequest request) {

        System.out.println("requestSRIService");

        RequestSRIServiceResponse response = new RequestSRIServiceResponse();
        
        response.setResponse("ok");
        
        return response;
    }
}
