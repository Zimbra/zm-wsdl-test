/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite Server
 * Copyright (C) 2016 Zimbra, Inc.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software Foundation,
 * version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.wsdlto;

import org.apache.cxf.tools.wsdlto.WSDLToJava;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Tool used to generate Java WSDL client code to access a WSDL server starting with only
 * the WSDL definition.
 *
 * This performs exactly the same function as:
 *     org.apache.cxf.tools.wsdlto.WSDLToJava.main
 * except that it doesn't require that the trust store accepts the server certicate of any server
 * hosting the WSDL definition.
 */
public class WSDLToJavaWrapper {

    private static final Logger LOG = Logger.getLogger(WSDLToJavaWrapper.class);
    static {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);
        LOG.setLevel(Level.INFO);
    }

    public static void main(String args[]) throws java.lang.Exception {
        // Create a trust manager that does not validate certificate chains
        // without this, we need to import the server certificate into the trust store.
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[]{
                new javax.net.ssl.X509TrustManager() {
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                    @Override
                    public void checkClientTrusted( java.security.cert.X509Certificate[] certs, String authType) { }
                    @Override
                    public void checkServerTrusted( java.security.cert.X509Certificate[] certs, String authType) { }
                    }
                };
        // Install the all-trusting trust manager
        try {
            javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            LOG.info("Problem installing the all-trusting trust manager", e);
        }

        // The above isn't necessarily enough if the server hostname doesn't agree with the name in
        // the certificate.
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
            @Override
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                return true;
            }
        });
        org.apache.cxf.tools.wsdlto.WSDLToJava.main(args);
    }
}
