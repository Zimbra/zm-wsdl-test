/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite Server
 * Copyright (C) 2011, 2013, 2014 Zimbra, Inc.
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
package com.zimbra.soap.admin;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Element;

import com.sun.xml.ws.developer.WSBindingProvider;
import com.zimbra.soap.Utility;

import generated.zcsclient.admin.testAdminZimletConfigInfo;
import generated.zcsclient.admin.testAdminZimletContext;
import generated.zcsclient.admin.testAdminZimletDesc;
import generated.zcsclient.admin.testAdminZimletInfo;
import generated.zcsclient.admin.testAttr;
import generated.zcsclient.admin.testGetAdminExtensionZimletsRequest;
import generated.zcsclient.admin.testGetAdminExtensionZimletsResponse;
import generated.zcsclient.admin.testGetAdminExtensionZimletsResponse.Zimlets;
import generated.zcsclient.admin.testGetAllZimletsRequest;
import generated.zcsclient.admin.testGetAllZimletsResponse;
import generated.zcsclient.admin.testGetZimletRequest;
import generated.zcsclient.admin.testGetZimletResponse;
import generated.zcsclient.admin.testGetZimletStatusRequest;
import generated.zcsclient.admin.testGetZimletStatusResponse;
import generated.zcsclient.admin.testZimletInfo;
import generated.zcsclient.admin.testZimletServerExtension;
import generated.zcsclient.admin.testZimletStatus;
import generated.zcsclient.admin.testZimletStatusCos;
import generated.zcsclient.admin.testZimletStatusParent;
import generated.zcsclient.admin.testZimletStatusSetting;
import generated.zcsclient.ws.service.ZcsAdminPortType;
import generated.zcsclient.zm.testNamedElement;

public class WSDLZimletTest {

    private static ZcsAdminPortType eif = null;

    @BeforeClass
    public static void init() throws Exception {
        Utility.setUpToAcceptAllHttpsServerCerts();
        eif = Utility.getAdminSvcEIF();
    }

    @Test
    public void getAllZimletsTest() throws Exception {
        Utility.addSoapAdminAuthHeader((WSBindingProvider)eif);
        testGetAllZimletsRequest req = new testGetAllZimletsRequest();
        req.setExclude("None");
        testGetAllZimletsResponse resp = eif.getAllZimletsRequest(req);
        Assert.assertNotNull("GetAllZimletsResponse object", resp);
        List<testZimletInfo> zimlets = resp.getZimlet();
        Assert.assertNotNull("zimlets list object", zimlets);
        int zNum = zimlets.size();
        Assert.assertTrue("Number of zimlets=" + zNum + "is at least 4", zNum >= 4);
        int cnt = 0;
        for (testZimletInfo zimlet : zimlets) {
            cnt++;
            String tag = "zimlet " + cnt;
            Assert.assertNotNull(tag + " id", zimlet.getId());
            Assert.assertNotNull(tag + " name", zimlet.getName());
            zimlet.getHasKeyword();  // Not required
            int aCnt = 0;
            for (testAttr attr : zimlet.getA()) {
                aCnt++;
                String aTag = tag + " attr " + aCnt;
                Assert.assertNotNull(aTag + " name", attr.getN());
                Assert.assertNotNull(aTag + " value", attr.getValue());
            }
        }
    }

    @Test
    public void getZimletTest() throws Exception {
        Utility.addSoapAdminAuthHeader((WSBindingProvider)eif);
        testGetZimletRequest req = new testGetZimletRequest();
        testNamedElement ne = new testNamedElement();
        ne.setName("com_zimbra_url");
        req.setZimlet(ne);
        testGetZimletResponse resp = eif.getZimletRequest(req);
        Assert.assertNotNull("GetZimletResponse object", resp);
        testZimletInfo zimlet = resp.getZimlet();
        String tag = "zimlet";
        Assert.assertNotNull(tag + " id", zimlet.getId());
        Assert.assertNotNull(tag + " name", zimlet.getName());
        zimlet.getHasKeyword();  // Not required
        int aCnt = 0;
        for (testAttr attr : zimlet.getA()) {
            aCnt++;
            String aTag = tag + " attr " + aCnt;
            Assert.assertNotNull(aTag + " name", attr.getN());
            Assert.assertNotNull(aTag + " value", attr.getValue());
        }
    }

    @Test
    public void getZimletStatusTest() throws Exception {
        Utility.addSoapAdminAuthHeader((WSBindingProvider)eif);
        testGetZimletStatusRequest req = new testGetZimletStatusRequest();
        testGetZimletStatusResponse resp = eif.getZimletStatusRequest(req);
        Assert.assertNotNull("GetZimletStatusResponse object", resp);
        testZimletStatusParent parent = resp.getZimlets();
        int zNum = parent.getZimlet().size();
        Assert.assertTrue("Number of zimlets=" + zNum + "is at least 4", zNum >= 4);
        int zCnt = 0;
        for (testZimletStatus zimlet : parent.getZimlet()) {
            zCnt++;
            String zTag = "zimlet " + zCnt;
            Assert.assertNotNull(zTag + " name", zimlet.getName());
            Assert.assertTrue(zTag + " priority >= 0", zimlet.getPriority() >= 0);
            Assert.assertEquals(zTag + " status", testZimletStatusSetting.ENABLED, zimlet.getStatus());
            // ZimbraServer deployed zimlets happen to have false for all
            // zimlets but ZimbraNetwork has some extensions.
            // Changed test to just be for existence of "isExtension" method.
            zimlet.isExtension();
        }
        List<testZimletStatusCos> coses = resp.getCos();
        zNum = coses.size();
        Assert.assertTrue("Number of zimlets=" + zNum + "is at least 1", zNum >= 1);
        int cCnt = 0;
        for (testZimletStatusCos cos : coses) {
            cCnt++;
            String cTag = "cos " + cCnt;
            Assert.assertNotNull(cTag + " name", cos.getName());
            zCnt = 0;
            for (testZimletStatus zimlet : cos.getZimlet()) {
                zCnt++;
                String zTag = cTag + " zimlet " + zCnt;
                Assert.assertNotNull(zTag + " name", zimlet.getName());
                zimlet.getPriority();  // probably null
                Assert.assertEquals(zTag + " status", testZimletStatusSetting.ENABLED, zimlet.getStatus());
                Assert.assertFalse(zTag + " extension setting", zimlet.isExtension());
            }
        }
    }

    //  ZimbraNetwork's "ant dev-deploy-all" installs some Admin extensions
    //  (ZimbraServer does not)
    @Test
    public void getAdminExtensionZimletsTest() throws Exception {
        ZcsAdminPortType nvEif = Utility.getNonValidatingAdminSvcEIF();
        // the validator does not like the @XmlAnyElement used
        // in AdminZimletDesc
        Utility.addSoapAdminAuthHeader((WSBindingProvider)nvEif);
        testGetAdminExtensionZimletsRequest req = new testGetAdminExtensionZimletsRequest();
        testGetAdminExtensionZimletsResponse resp = nvEif.getAdminExtensionZimletsRequest(req);
        Assert.assertNotNull("GetAdminExtensionZimletsResponse object", resp);
        Zimlets zimlets = resp.getZimlets();
        Assert.assertNotNull("GetAdminExtensionZimletsResponse/zimlets object", zimlets);
        List<testAdminZimletInfo> azimlets = zimlets.getZimlet();
        Utility.LOG.debug("Number of zimlets=" + azimlets.size());
        for (testAdminZimletInfo azi : azimlets) {
            testAdminZimletContext ctx = azi.getZimletContext();
            if (ctx != null) {
                Assert.assertNotNull("ZimletContext baseUrl object", ctx.getBaseUrl());
                Utility.LOG.debug("zimlet context baseUrl=" + ctx.getBaseUrl());
                Assert.assertNotNull("ZimletContext presence object", ctx.getPresence());
                ctx.getPriority(); // optional
            }
            testAdminZimletConfigInfo cfg = azi.getZimletConfig();
            if (cfg != null) {
                Assert.assertNotNull("ZimletConfig description object", cfg.getDescription());
                Assert.assertNotNull("ZimletConfig extension object", cfg.getExtension());
                Assert.assertNotNull("ZimletConfig name object", cfg.getName());
                Utility.LOG.debug("zimlet name=" + cfg.getName());
                cfg.getLabel();
                cfg.getTarget();
                cfg.getVersion();
                cfg.getGlobal();
                cfg.getHost();
            }
            testAdminZimletDesc zimletDesc = azi.getZimlet();
            if (zimletDesc != null) {
                Assert.assertNotNull("ZimletDesc description object", zimletDesc.getDescription());
                Assert.assertNotNull("ZimletDesc extension object", zimletDesc.getExtension());
                Assert.assertNotNull("ZimletDesc name object", zimletDesc.getName());
                Utility.LOG.debug("zimlet name=" + zimletDesc.getName());
                zimletDesc.getLabel();
                zimletDesc.getTarget();
                zimletDesc.getVersion();
                List<Object> objs = zimletDesc.getServerExtensionOrIncludeOrIncludeCSS();  // if @XmlAnyElement
                // List<JAXBElement<?>> objs = zimletDesc.getServerExtensionOrIncludeOrIncludeCSS(); // if @xmlMixed
                // List<Serializable> objs = zimletDesc.getContent(); // if nothing
                for (Object obj : objs) {
                    if (obj instanceof Element) {
                        Element elem = (Element)obj;
                        Utility.LOG.debug("getAdminExtensionZimletsTest - Element name " + elem.getLocalName());
                    } else if (obj instanceof testZimletServerExtension) {
                        testZimletServerExtension zse = (testZimletServerExtension) obj;
                        Assert.assertNotNull("ZimletDesc server extension HasKeyword object", zse.getHasKeyword());
                        zse.getExtensionClass();
                        zse.getRegex();
                    } else if (obj instanceof JAXBElement) {
                        @SuppressWarnings("rawtypes")
                        JAXBElement jaxbElem = (JAXBElement) obj;
                        @SuppressWarnings("rawtypes")
                        Class klass = jaxbElem.getDeclaredType();
                        Utility.LOG.debug("ZimletDesc klass wrapped by JAXBElement=" + klass.getName() +
                                " elemName=" + jaxbElem.getName() + " value=" + jaxbElem.getValue().toString());
                    }
                }
            }
            @SuppressWarnings("unused")
            Element handlerCfgElem = azi.getAny();
        }
    }
}
