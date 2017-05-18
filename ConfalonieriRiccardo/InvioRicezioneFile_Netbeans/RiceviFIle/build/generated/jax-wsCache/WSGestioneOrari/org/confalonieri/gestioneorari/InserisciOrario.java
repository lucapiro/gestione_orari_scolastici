
package org.confalonieri.gestioneorari;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per InserisciOrario complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InserisciOrario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nomeProf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ora" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="giorno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="classe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InserisciOrario", propOrder = {
    "nomeProf",
    "ora",
    "giorno",
    "classe",
    "aula"
})
public class InserisciOrario {

    protected String nomeProf;
    protected int ora;
    protected int giorno;
    protected String classe;
    protected String aula;

    /**
     * Recupera il valore della proprietà nomeProf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeProf() {
        return nomeProf;
    }

    /**
     * Imposta il valore della proprietà nomeProf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeProf(String value) {
        this.nomeProf = value;
    }

    /**
     * Recupera il valore della proprietà ora.
     * 
     */
    public int getOra() {
        return ora;
    }

    /**
     * Imposta il valore della proprietà ora.
     * 
     */
    public void setOra(int value) {
        this.ora = value;
    }

    /**
     * Recupera il valore della proprietà giorno.
     * 
     */
    public int getGiorno() {
        return giorno;
    }

    /**
     * Imposta il valore della proprietà giorno.
     * 
     */
    public void setGiorno(int value) {
        this.giorno = value;
    }

    /**
     * Recupera il valore della proprietà classe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClasse() {
        return classe;
    }

    /**
     * Imposta il valore della proprietà classe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClasse(String value) {
        this.classe = value;
    }

    /**
     * Recupera il valore della proprietà aula.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAula() {
        return aula;
    }

    /**
     * Imposta il valore della proprietà aula.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAula(String value) {
        this.aula = value;
    }

}
