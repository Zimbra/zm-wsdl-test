
package zimbra.generated.adminclient.mail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import zimbra.generated.adminclient.zm.testId;


/**
 * <p>Java class for sendMsgResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendMsgResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="m" type="{urn:zimbra}id"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendMsgResponse", propOrder = {
    "m"
})
public class testSendMsgResponse {

    @XmlElement(required = true)
    protected testId m;

    /**
     * Gets the value of the m property.
     * 
     * @return
     *     possible object is
     *     {@link testId }
     *     
     */
    public testId getM() {
        return m;
    }

    /**
     * Sets the value of the m property.
     * 
     * @param value
     *     allowed object is
     *     {@link testId }
     *     
     */
    public void setM(testId value) {
        this.m = value;
    }

}