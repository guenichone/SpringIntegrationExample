package org.barrak.springintegration;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        // ???
        servlet.setTransformWsdlLocations(false);
        // Servlet listening url 
        return new ServletRegistrationBean(servlet, "/services/*");
    }

    @Bean(name = "SRIGenericRequest")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema SRIGenericRequestSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        // Url of the WSDL file (http://localhost:8081/services/SRIGenericRequest.wsdl)
        wsdl11Definition.setPortTypeName("SRIGenericRequest");
        // Url of the service to post request (http://localhost:8081/services/sri)
        wsdl11Definition.setLocationUri("/sri");
        wsdl11Definition.setTargetNamespace("http://webservice.springintegration.barrak.org/esbdt/schemas");
        wsdl11Definition.setSchema(SRIGenericRequestSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema getSRIGenericRequestSchema() {
        return new SimpleXsdSchema(new ClassPathResource("SRIGenericRequest.xsd"));
    }
}
