package com.framework.utils.driver;

import com.framework.utils.common.WebMessages;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebSiteBase {

    public WebDriver driver;

    public void driverInit() {

        Properties prop = new Properties();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            // String fileName = System.getProperty("configFileName");
            // System.out.println("Config File Chosen --> " + fileName);
            File file = new File(classLoader.getResource("data/env0_config.properties").getFile());
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
        } catch (IOException e) {
            System.out.println("File not found");
        }

        //Takes the properties from config.properties file
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        String url = prop.getProperty("url");

        System.out.println("Browser Selected => " + browserName);

        if (browserName.equalsIgnoreCase("chrome")) {
            if(SystemUtils.IS_OS_LINUX) {
                WebDriverManager.chromedriver().browserPath("/opt/hostedtoolcache/chromium/latest/x64/chrome");
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("opera")) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
            driver = new SafariDriver();
        } else {
            System.out.println("No browser detected");
        }

        System.out.println("Property loaded" + url);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(url);

        System.out.println("Load Webapp Messages");
        WebMessages webMessages = new WebMessages();
        webMessages.load();
    }
}
