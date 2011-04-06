
package com.zimbra.soap.mail.wsimport.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getConvResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getConvResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="c" type="{urn:zimbraMail}conversationInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getConvResponse", propOrder = {
    "c"
})
public class GetConvResponse {

    @XmlElement(required = true)
    protected ConversationInfo c;

    /**
     * Gets the value of the c property.
     * 
     * @return
     *     possible object is
     *     {@link ConversationInfo }
     *     
     */
    public ConversationInfo getC() {
        return c;
    }

    /**
     * Sets the value of the c property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConversationInfo }
     *     
     */
    public void setC(ConversationInfo value) {
        this.c = value;
    }

}
