
package zimbra.generated.adminclient.admin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import zimbra.generated.adminclient.zm.testAccountSelector;


/**
 * <p>Java class for addAccountLoggerRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addAccountLoggerRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="logger" type="{urn:zimbraAdmin}loggerInfo"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="account" type="{urn:zimbra}accountSelector"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addAccountLoggerRequest", propOrder = {
    "logger",
    "id",
    "account"
})
public class testAddAccountLoggerRequest {

    @XmlElement(required = true)
    protected testLoggerInfo logger;
    protected String id;
    protected testAccountSelector account;

    /**
     * Gets the value of the logger property.
     * 
     * @return
     *     possible object is
     *     {@link testLoggerInfo }
     *     
     */
    public testLoggerInfo getLogger() {
        return logger;
    }

    /**
     * Sets the value of the logger property.
     * 
     * @param value
     *     allowed object is
     *     {@link testLoggerInfo }
     *     
     */
    public void setLogger(testLoggerInfo value) {
        this.logger = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link testAccountSelector }
     *     
     */
    public testAccountSelector getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link testAccountSelector }
     *     
     */
    public void setAccount(testAccountSelector value) {
        this.account = value;
    }

}
