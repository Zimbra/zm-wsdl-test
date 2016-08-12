# zm-wsdl-test Repository

This repository hosts a java client built on top of Zimbra's WSDL definition.
This acts as both a proof of concept for potential WSDL client developers and a test suite for some
Zimbra functionality.

## Inputs from Perforce

From `ZimbraSoap`:

- `./build.xml`
- `./src/wsdl-test/com`
- `./src/wsdl-test/wsdlBindings-zcs.xml`
- `./src/wsdl-test/xsdBindings-zcs.xml`

## Dependencies

- `zm-common`
- `zm-thirdparty-jars`

## Artifacts

- N/A Sole purpose of this repo is to demonstrate/test Zimbra's WSDL support.  

## Requirements

See `src/java-test/test.properties`.

* Zimbra Network Edition must be installed on the same system as these tests are run.
* An **admin** user must exist in the default domain.  
  The Admin's name must match the value for the key `adminName` in `src/java-test/test.properties`
  (default **admin**)  
  The Admin's password must match the value for the key `adminPass` in `src/java-test/test.properties`
  (default **test123**)
* A user with name **user1** must exist in the default domain.  
  The **user1** password must match the value for the key `otherUsersPass` in `src/java-test/test.properties`
  (default **test123**)

### The ant "test" target

These tests are intended to be a clean room test of Zimbra's WSDL, with no dependencies on Zimbra product
source code - only using the `.wsdl` and `.xsd` files discoverable from the installed Zimbra Server.

The tests rely on Java API files automatically generated from Zimbra's WSDL definition.

The main intention is to validate that correct, working WSDL client software can be written based on our WSDL.

Some tests rely on the data that the classic `zm-store` RunUnitTests framework rely on.  For instance,
certain data loaded into the **user1** mailbox and some other resources existing.

Some of the tests create and delete mailboxes and other Zimbra artefacts as part of their execution.

### TODO

It would be good to have demonstration clients using other programming languages based on our WSDL
