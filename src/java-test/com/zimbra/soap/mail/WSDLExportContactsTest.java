/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite Server
 * Copyright (C) 2010, 2011, 2013, 2014 Zimbra, Inc.
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
package com.zimbra.soap.mail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.xml.ws.developer.WSBindingProvider;
import com.zimbra.soap.Utility;

import generated.zcsclient.mail.testContent;
import generated.zcsclient.mail.testExportContactsRequest;
import generated.zcsclient.mail.testExportContactsResponse;
import generated.zcsclient.mail.testImportContact;
import generated.zcsclient.mail.testImportContactsRequest;
import generated.zcsclient.mail.testImportContactsResponse;
import generated.zcsclient.ws.service.ZcsPortType;

public class WSDLExportContactsTest {

    private static ZcsPortType mailSvcEIF;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @BeforeClass
    public static void init() throws Exception {
        mailSvcEIF = Utility.getZcsSvcEIF();
    }

    public void doImport() throws Exception {
        testImportContactsRequest req = new testImportContactsRequest();
        req.setCt("csv");
        testContent theContent = new testContent();
        StringBuilder contentB = new StringBuilder();
        contentB.append("\"assistantPhone\",\"email\",\"firstName\",\"lastName\"\n");
        contentB.append("\"000-1112\",\"kiki@example.org\",\"Kiki\",\"Dee\"");
        theContent.setValue(contentB.toString());
        req.setContent(theContent);
        testImportContactsResponse resp = mailSvcEIF.importContactsRequest(req);
        Assert.assertNotNull("ImportContactsResponse object", resp);
        testImportContact contact = resp.getCn();
        Assert.assertNotNull("<cn> contents", contact);
        Assert.assertEquals("Number of contacts imported", 1, contact.getN());
        String ids = contact.getIds();
        Assert.assertNotNull("Contact ids", ids);
        Assert.assertTrue("Contact ids sting length >= 2", (ids.length() > 1));
    }

    /**
     * Current assumption : user1 exists with password test123
     */
    @Test
    public void defaultZimbraFmt() throws Exception {
       testExportContactsRequest req = new testExportContactsRequest();
       req.setCt("csv");
       Utility.addSoapAcctAuthHeader((WSBindingProvider)mailSvcEIF);
       testExportContactsResponse resp = mailSvcEIF.exportContactsRequest(req);
       Assert.assertNotNull("ExportContactsResponse object", resp);
       String content = resp.getContent();
       Assert.assertNotNull("<content> contents", content);
       if (content.length() == 0) {
           doImport();  // Seed account with at least one contact
           resp = mailSvcEIF.exportContactsRequest(req);
           Assert.assertNotNull("ExportContactsResponse object", resp);
           content = resp.getContent();
           Assert.assertNotNull("<content> contents", content);
       }
       String firstPart = content.length() <= 17 ? content : content.substring(0, 17);
       Assert.assertEquals("First part of <content> ", "\"assistantPhone\",", firstPart);
    }

    @Test
    public void winLiveFrenchSemicolon() throws Exception {
       testExportContactsRequest req = new testExportContactsRequest();
       req.setCt("csv");
       req.setCsvfmt("windows-live-mail-csv");
       req.setCsvlocale("fr");
       req.setCsvsep(";");
       Utility.addSoapAcctAuthHeader((WSBindingProvider)mailSvcEIF);
       testExportContactsResponse resp = mailSvcEIF.exportContactsRequest(req);
       Assert.assertNotNull("ExportContactsResponse object", resp);
       String content = resp.getContent();
       Assert.assertNotNull("<content> contents", content);
       if (content.length() == 0) {
           doImport();  // Seed account with at least one contact
           resp = mailSvcEIF.exportContactsRequest(req);
           Assert.assertNotNull("ExportContactsResponse object", resp);
           content = resp.getContent();
           Assert.assertNotNull("<content> contents", content);
       }
       String firstPart = content.length() <= 15 ? content : content.substring(0, 15);
       Assert.assertEquals("First part of <content> ", "\"Pr\u00E9nom\";\"Nom\";", firstPart);
    }
}
