
package org.confalonieri.gestioneorari;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.confalonieri.gestioneorari package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InserisciOrarioResponse_QNAME = new QName("http://gestioneOrari.confalonieri.org/", "InserisciOrarioResponse");
    private final static QName _ControllaUtente_QNAME = new QName("http://gestioneOrari.confalonieri.org/", "controllaUtente");
    private final static QName _InvioFileResponse_QNAME = new QName("http://gestioneOrari.confalonieri.org/", "InvioFileResponse");
    private final static QName _ControllaUtenteResponse_QNAME = new QName("http://gestioneOrari.confalonieri.org/", "controllaUtenteResponse");
    private final static QName _InserisciOrario_QNAME = new QName("http://gestioneOrari.confalonieri.org/", "InserisciOrario");
    private final static QName _InvioFile_QNAME = new QName("http://gestioneOrari.confalonieri.org/", "InvioFile");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.confalonieri.gestioneorari
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvioFileResponse }
     * 
     */
    public InvioFileResponse createInvioFileResponse() {
        return new InvioFileResponse();
    }

    /**
     * Create an instance of {@link InserisciOrarioResponse }
     * 
     */
    public InserisciOrarioResponse createInserisciOrarioResponse() {
        return new InserisciOrarioResponse();
    }

    /**
     * Create an instance of {@link ControllaUtente }
     * 
     */
    public ControllaUtente createControllaUtente() {
        return new ControllaUtente();
    }

    /**
     * Create an instance of {@link ControllaUtenteResponse }
     * 
     */
    public ControllaUtenteResponse createControllaUtenteResponse() {
        return new ControllaUtenteResponse();
    }

    /**
     * Create an instance of {@link InvioFile }
     * 
     */
    public InvioFile createInvioFile() {
        return new InvioFile();
    }

    /**
     * Create an instance of {@link InserisciOrario }
     * 
     */
    public InserisciOrario createInserisciOrario() {
        return new InserisciOrario();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InserisciOrarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestioneOrari.confalonieri.org/", name = "InserisciOrarioResponse")
    public JAXBElement<InserisciOrarioResponse> createInserisciOrarioResponse(InserisciOrarioResponse value) {
        return new JAXBElement<InserisciOrarioResponse>(_InserisciOrarioResponse_QNAME, InserisciOrarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ControllaUtente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestioneOrari.confalonieri.org/", name = "controllaUtente")
    public JAXBElement<ControllaUtente> createControllaUtente(ControllaUtente value) {
        return new JAXBElement<ControllaUtente>(_ControllaUtente_QNAME, ControllaUtente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvioFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestioneOrari.confalonieri.org/", name = "InvioFileResponse")
    public JAXBElement<InvioFileResponse> createInvioFileResponse(InvioFileResponse value) {
        return new JAXBElement<InvioFileResponse>(_InvioFileResponse_QNAME, InvioFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ControllaUtenteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestioneOrari.confalonieri.org/", name = "controllaUtenteResponse")
    public JAXBElement<ControllaUtenteResponse> createControllaUtenteResponse(ControllaUtenteResponse value) {
        return new JAXBElement<ControllaUtenteResponse>(_ControllaUtenteResponse_QNAME, ControllaUtenteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InserisciOrario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestioneOrari.confalonieri.org/", name = "InserisciOrario")
    public JAXBElement<InserisciOrario> createInserisciOrario(InserisciOrario value) {
        return new JAXBElement<InserisciOrario>(_InserisciOrario_QNAME, InserisciOrario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvioFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gestioneOrari.confalonieri.org/", name = "InvioFile")
    public JAXBElement<InvioFile> createInvioFile(InvioFile value) {
        return new JAXBElement<InvioFile>(_InvioFile_QNAME, InvioFile.class, null, value);
    }

}
