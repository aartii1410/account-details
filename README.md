# Account Transaction Details

A microservice that provides 2 API endpoints to retrieve the account details and account transactions details

#Development
The project uses java JDK 11 or higher, if you need to use both JDK 8 and JDK 11 on command line, please follow these steps
1. Download and install JDK 8 and JDK 11
2. Add the following aliases to your .bash_profile file in the home directory
alias j11="export JAVA_HOME=`/usr/libexec/java_home -v 11`; java -version"
alias j8="export JAVA_HOME=`/usr/libexec/java_home -v 1.8`; java -version"
. You can switch between the JDKs by running the alias commads e.g. `j11` will change to JDK 11

NOTE: Which ever JDK you want to set as the default, export it as JAVA HOME env e.g. `export JAVA_HOME=`/usr/libexec/java_home -v 1.8``

## Install Lombok plugin
A plugin that adds first-class support for Project Lombok .Project Lombok is a java library that
automatically plugs into your editor and build tools and auto generates the getters,setters,equals,
hashcode and toString methods with the annotations.

Important: Lombok Requires Annotation Processing. For the plugin to function correctly, please enable
it under "Settings > Build > Compiler > Annotation Processors".

To install the Lombok plugin
* Go to File > Settings > Plugins
* Click on Browse repositories
* Search for Lombok Plugin
* Click on Install plugin
* Go to Preferences -> Build, Execution and Deployment -> Annotation Processors -> Tick the checkbox against 'Enable Annotation Processing'
* Restart IntelliJ IDEA

4. To start the application
right click on main class AccountTransactionsApplication.java and run as java application
login to h2 database console to check the data loaded in database and application will be started on port 9090.
http://localhost:9090/h2-console
once the application is started hit below url on browser
http://localhost:9090/swagger-ui.html

