
package com.zimbra.soap.admin.wsimport.generated;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-hudson-48-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "AdminService", targetNamespace = "urn:zimbraAdmin")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AdminService {


    /**
     * 
     * @param parameters
     * @return
     *     returns com.zimbra.soap.admin.wsimport.generated.AuthResponse
     */
    @WebMethod(action = "urn:zimbraAdmin/Auth")
    @WebResult(name = "AuthResponse", targetNamespace = "urn:zimbraAdmin", partName = "parameters")
    public AuthResponse authRequest(
        @WebParam(name = "AuthRequest", targetNamespace = "urn:zimbraAdmin", partName = "parameters")
        AuthRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.zimbra.soap.admin.wsimport.generated.CreateAccountResponse
     */
    @WebMethod(action = "urn:zimbraAdmin/CreateAccount")
    @WebResult(name = "CreateAccountResponse", targetNamespace = "urn:zimbraAdmin", partName = "parameters")
    public CreateAccountResponse createAccountRequest(
        @WebParam(name = "CreateAccountRequest", targetNamespace = "urn:zimbraAdmin", partName = "parameters")
        CreateAccountRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.zimbra.soap.admin.wsimport.generated.GetAccountResponse
     */
    @WebMethod(action = "urn:zimbraAdmin/GetAccount")
    @WebResult(name = "GetAccountResponse", targetNamespace = "urn:zimbraAdmin", partName = "parameters")
    public GetAccountResponse getAccountRequest(
        @WebParam(name = "GetAccountRequest", targetNamespace = "urn:zimbraAdmin", partName = "parameters")
        GetAccountRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.zimbra.soap.admin.wsimport.generated.ReloadLocalConfigResponse
     */
    @WebMethod(action = "urn:zimbraAdmin/ReloadLocalConfig")
    @WebResult(name = "ReloadLocalConfigResponse", targetNamespace = "urn:zimbraAdmin", partName = "parameters")
    public ReloadLocalConfigResponse reloadLocalConfigRequest(
        @WebParam(name = "ReloadLocalConfigRequest", targetNamespace = "urn:zimbraAdmin", partName = "parameters")
        ReloadLocalConfigRequest parameters);

}
