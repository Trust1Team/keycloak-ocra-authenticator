# keycloak-ocra-authenticator
[![][t1t-logo]][Trust1Connector-url]

To install the OCRA Authenticator one has to:

* Add ocra config
  * `$ mkdir /usr/local/ocra`
  * `$ touch ocra.conf`
  * `keycloak-ocra {
       ocra: {
         seed: "<ora_seed>",
         algorithm: "<ocra_algo>",
       }
     }`

* Add the jar to the Keycloak server:
  * `$ cp target/keycloak-ocra-authenticator-*.jar _KEYCLOAK_HOME_/providers/`

* Add three templates to the Keycloak server:
  * `$ cp templates/ocra-validation.ftl _KEYCLOAK_HOME_/themes/base/login/`
  * `$ cp templates/ocra-validation-error.ftl _KEYCLOAK_HOME_/themes/base/login/`
  * `$ cp templates/ocra-validation-mobile-number.ftl _KEYCLOAK_HOME_/themes/base/login/`


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


[Trust1Team-url]: http://trust1team.com
[Trust1Connector-url]: http://www.trust1connector.com
[t1t-logo]: http://imgur.com/lukAaxx.png
[t1c-logo]: http://i.imgur.com/We0DIvj.png
[t1t-conflu-gcl]: https://trust1t.atlassian.net/wiki/display/NPAPI/Generic+Connector+Library