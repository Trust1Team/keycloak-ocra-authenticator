# keycloak-ocra-authenticator
[![][t1t-logo]][Trust1Connector-url]

To install the OCRA Authenticator one has to:

* Add ocra config
  * `$ mkdir /usr/local/ocra`
  * `$ touch ocra.conf`
  * `keycloak-ocra {
       apikey: "<your-api-key>",
       ocra-api-uri: "https://apim.t1t.be/trust1team/ocra-api/v1",
       sms-api-uri: "https://apim.t1t.be/trust1team/sms-api/v1",
       ocra: {
         seed: "<ora_seed>",
         algorithm: "<ocra_algo>",
       }
     }`
The config will be applicable to all realms using OCRA execution in a flow.

* Add the jar to the Keycloak server:
  * `$ cp target/keycloak-ocra-authenticator-*.jar _KEYCLOAK_HOME_/providers/`

* Add three templates to the Keycloak server:
  * `$ cp templates/ocra-validation.ftl _KEYCLOAK_HOME_/themes/base/login/`
  * `$ cp templates/ocra-validation-error.ftl _KEYCLOAK_HOME_/themes/base/login/`
  * `$ cp templates/ocra-validation-mobile-number.ftl _KEYCLOAK_HOME_/themes/base/login/`

If you want to retrieve the files from a repo, you can install wget (yum install wget).

Configure your REALM to use the OCRA Authentication.
First create a new REALM (or select a previously created REALM).

Under Authentication > Flows:
* Copy 'Browse' flow to 'Browser with OCRA' flow
* Click on 'Actions > Add execution on the 'Browser with OCRA Forms' line and add the 'OCRA Authentication'
* Set 'OCRA Authentication' to 'REQUIRED' or 'ALTERNATIVE'
* To configure the OCRA Authenticator, click on Actions  Config and fill in the attributes.

Under Authentication > Bindings:
* Select 'Browser with OCRA' as the 'Browser Flow' for the REALM.

Under Authentication > Required Actions:
* Click on Register and select 'OCRA Authentication' to add the Required Action to the REALM.
* Make sure that for the 'OCRA Authentication' both the 'Enabled' and 'Default Action' check boxes are checked.
* Click on Register and select 'Mobile Number' to add the Required Action to the REALM.
* Make sure that for the 'Mobile Number' both the 'Enabled' and 'Default Action' check boxes are checked.

# Additional tips
Run a docker jboss/keycloak (-p hostport:containerport):

`docker run --name keycloak -p 9000:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -e KEYCLOAK_LOGLEVEL=DEBUG jboss/keycloak:3.4.3.Final`

The jboss/keycloak is a centos:7, if you want to login as root using docker (use user ID=0) and attach your terminal (in order to deploy the ear)

`$ docker exec -u 0 -it keycloak bash`

[Trust1Team-url]: http://trust1team.com
[Trust1Connector-url]: http://www.trust1connector.com
[t1t-logo]: http://imgur.com/lukAaxx.png
[t1c-logo]: http://i.imgur.com/We0DIvj.png
[t1t-conflu-gcl]: https://trust1t.atlassian.net/wiki/display/NPAPI/Generic+Connector+Library