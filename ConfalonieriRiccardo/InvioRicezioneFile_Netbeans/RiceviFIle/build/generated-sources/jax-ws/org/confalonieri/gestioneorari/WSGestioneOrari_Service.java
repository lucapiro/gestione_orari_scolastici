
package org.confalonieri.gestioneorari;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WSGestioneOrari", targetNamespace = "http://gestioneOrari.confalonieri.org/", wsdlLocation = "http://localhost:8080/ProviderJava/WSGestioneOrari?wsdl")
public class WSGestioneOrari_Service
    extends Service
{

    private final static URL WSGESTIONEORARI_WSDL_LOCATION;
    private final static WebServiceException WSGESTIONEORARI_EXCEPTION;
    private final static QName WSGESTIONEORARI_QNAME = new QName("http://gestioneOrari.confalonieri.org/", "WSGestioneOrari");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ProviderJava/WSGestioneOrari?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSGESTIONEORARI_WSDL_LOCATION = url;
        WSGESTIONEORARI_EXCEPTION = e;
    }

    public WSGestioneOrari_Service() {
        super(__getWsdlLocation(), WSGESTIONEORARI_QNAME);
    }

    public WSGestioneOrari_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSGESTIONEORARI_QNAME, features);
    }

    public WSGestioneOrari_Service(URL wsdlLocation) {
        super(wsdlLocation, WSGESTIONEORARI_QNAME);
    }

    public WSGestioneOrari_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSGESTIONEORARI_QNAME, features);
    }

    public WSGestioneOrari_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSGestioneOrari_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WSGestioneOrari
     */
    @WebEndpoint(name = "WSGestioneOrariPort")
    public WSGestioneOrari getWSGestioneOrariPort() {
        return super.getPort(new QName("http://gestioneOrari.confalonieri.org/", "WSGestioneOrariPort"), WSGestioneOrari.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSGestioneOrari
     */
    @WebEndpoint(name = "WSGestioneOrariPort")
    public WSGestioneOrari getWSGestioneOrariPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://gestioneOrari.confalonieri.org/", "WSGestioneOrariPort"), WSGestioneOrari.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSGESTIONEORARI_EXCEPTION!= null) {
            throw WSGESTIONEORARI_EXCEPTION;
        }
        return WSGESTIONEORARI_WSDL_LOCATION;
    }

}
