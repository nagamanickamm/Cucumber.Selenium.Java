# CucumberJavaSelenium
Using PlayTech.com

Code Structure:
1. Cucumber Feature files are placed under src.test.java.resources.features
2. Cucumber Step definition file is placed under com.test.stepdefinition package
3. Page files are java.com.framework.utils.pages package
4. Main driver initialisation java.com.test.framework.utils.driver package
5. Common driver functions initialisation java.com.test.framework.utils.common package
6. build.gradle contains all the dependencies libraries and has the tasks for executing the tests


Setup & test:
1. Install Gradle on your machine https://gradle.org/install/
2. Download remote repository https://github.com/nagamanickamm/org.playtech.bdd
3. Open the project and select build.gradle using your choice of IDE (Note: This project is build using IntelliJ)
   Import the project as gradle project
4. Go to Terminal, from project root (ex:'org.playtech.bdd') and enter "gradle --refresh-dependencies" (without quotes)
5. type "gradle cucumberTest -Pbrowser=firefox" (change your browser accordingly)
6. When the test is a completed click on the cucumber report link shown in the terminal
   or go to https://reports.cucumber.io/report-collections/4e61ccdb-f913-4909-bee5-ef24ce5695a6
7. Alternatively you can execute from run command in intelliJ or any iDE, please add -Dbrowser=firefox in VM options
8. If you have any issues with browser parameter then please comment systemProperties['browser'] = browser in build.gradle and it should take from properties file
   Notes:
- Sample Cloud report - https://reports.cucumber.io/reports/f4bc2fca-1ab1-4aad-8ca6-6c5a1fd95686

9. Alternatively run only selected tags using below
   gradle cucumberTest -Pbrowser=firefox -Ptags="@Smoke or @NoSmoke"


Easy steps:
1. Simply download the code as ZIP from git repo https://github.com/nagamanickamm/org.playtech.bdd
2. Go to the project folder (Make sure gradle is installed on your machine)
3. Enter : gradle cucumberTest -Pbrowser=firefox (tested on firefox and chrome)

1. Right click on Runner and execute in IntelliJ


Further enhancements:
1. Additional reporting plugins such as Allure, extent, cucumber report plugins cab be added depending on need
2. Further, cloud based AI reporting can be added using ReportPortal IO
3. CI CD can be setup easily with gradle options
4. Environment config file can be further enhanced to switch between multiple environments
5. Passwords can be set using secret key such as in Jenkins configurations
6. Use API calls to generate Test Data
7. Use Example option on cucumber to add Data driven test
8. Add @<tag> annotations to run only selected area of features
9. Enable Parallel runs across test and browser
10. Crossbrowser platforms such as Selenoid, Saucelabs, Browserstacks can be added
