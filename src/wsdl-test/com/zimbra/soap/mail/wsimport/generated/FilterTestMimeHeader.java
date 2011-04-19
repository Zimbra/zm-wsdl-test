
package com.zimbra.soap.mail.wsimport.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for filterTestMimeHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filterTestMimeHeader">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:zimbraMail}filterTestInfo">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="header" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="stringComparison" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="caseSensitive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filterTestMimeHeader")
public class FilterTestMimeHeader
    extends FilterTestInfo
{

    @XmlAttribute
    protected String header;
    @XmlAttribute
    protected String stringComparison;
    @XmlAttribute
    protected String value;
    @XmlAttribute
    protected Boolean caseSensitive;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeader(String value) {
        this.header = value;
    }

    /**
     * Gets the value of the stringComparison property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringComparison() {
        return stringComparison;
    }

    /**
     * Sets the value of the stringComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringComparison(String value) {
        this.stringComparison = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the caseSensitive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * Sets the value of the caseSensitive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCaseSensitive(Boolean value) {
        this.caseSensitive = value;
    }

}
