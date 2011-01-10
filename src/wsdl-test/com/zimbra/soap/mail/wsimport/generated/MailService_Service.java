
package com.zimbra.soap.mail.wsimport.generated;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-hudson-48-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "MailService", targetNamespace = "urn:zimbraMail", wsdlLocation = "file:/Users/gren/p4/gelliot_mac/main/ZimbraSoap/build/schema/MailService.wsdl")
public class MailService_Service
    extends Service
{

    private final static URL MAILSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.zimbra.soap.mail.wsimport.generated.MailService_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.zimbra.soap.mail.wsimport.generated.MailService_Service.class.getResource(".");
            url = new URL(baseUrl, "file:/Users/gren/p4/gelliot_mac/main/ZimbraSoap/build/schema/MailService.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/Users/gren/p4/gelliot_mac/main/ZimbraSoap/build/schema/MailService.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        MAILSERVICE_WSDL_LOCATION = url;
    }

    public MailService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MailService_Service() {
        super(MAILSERVICE_WSDL_LOCATION, new QName("urn:zimbraMail", "MailService"));
    }

    /**
     * 
     * @return
     *     returns MailService
     */
    @WebEndpoint(name = "MailServicePort")
    public MailService getMailServicePort() {
        return super.getPort(new QName("urn:zimbraMail", "MailServicePort"), MailService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MailService
     */
    @WebEndpoint(name = "MailServicePort")
    public MailService getMailServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("urn:zimbraMail", "MailServicePort"), MailService.class, features);
    }

}