package au.com.mag.webtests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;
import java.net.URL;

import static org.junit.Assert.assertNotEquals;

public class HomepageTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUpClass() {
        try {
            // Set the path to the Chrome binary
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
            System.out.println("Chrome binary path: " + options.getBrowserName());

            // Initialize the RemoteWebDriver
            URL remoteUrl = new URL("http://192.168.128.142:4444/wd/hub");
            driver = new RemoteWebDriver(remoteUrl, options);
            System.out.println("RemoteWebDriver initialized");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Before
    public void setUp() {
        // Open the Hugo website
        String url = "https://www.mag.com.au/";
        System.out.println("Opening URL: " + url);
        driver.get(url);

        // Wait for the page to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTitle() {
        String actualTitle = driver.getTitle();
        System.out.println("Actual title: " + actualTitle);
        String expectedTitle = "MAG is the most successful and longest running private flying group in Australia. MAG is run entirely not-for-profit operating out of Moorabbin Airport (YMMB), Melbourne’s largest GA airport. | Melbourne Aviation Group";
        assertTrue(actualTitle.contains(expectedTitle));
    }

    @Test
    public void testHeader() {
        WebElement header = driver.findElement(By.tagName("h1"));
        assertTrue(header.isDisplayed());
    }

    @Test
    public void testJoiningMag() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String originalUrl = driver.getCurrentUrl();

        // Find link within nav using XPath
        WebElement joiningLink = driver.findElement(By.xpath("//nav//a[contains(text(), 'Joining MAG')]"));

        joiningLink.click();

        // Wait for navigation to complete
        wait.until(webDriver -> !webDriver.getCurrentUrl().equals(originalUrl));

        // Verify we're on a different page
        assertNotEquals(originalUrl, driver.getCurrentUrl());

        assertTrue(driver.getPageSource().contains(
                "Any members looking to sell their share will be in contact. There is no fixed price for a share in the group, it is negotiated between the buyer and seller. You will be provided a copy of our latest financial statement in order to form a view on valuation."));

        // Go back and wait for the navigation to complete
        driver.navigate().back();
        wait.until(webDriver -> webDriver.getCurrentUrl().equals(originalUrl));
    }

    @Test
    public void testOurAircraftOky() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String originalUrl = driver.getCurrentUrl();

        // Find link within nav using XPath
        WebElement aircraftLink = driver.findElement(By.xpath("//button//span[contains(text(), 'Our Aircraft')]"));
        aircraftLink.click();
        
        WebElement okyLink = driver.findElement(By.xpath("//nav//a[contains(text(), 'VH-OKY - 1999 Piper Archer III')]"));
        okyLink.click();

        assertNotEquals(originalUrl, driver.getCurrentUrl());

        assertTrue(driver.getPageSource().contains(
                "The ubiquitous PA28 is familiar to most pilots, serving as a solid trainer and great entry aircraft for decades."));

        // Go back and wait for the navigation to complete
        driver.navigate().back();
        wait.until(webDriver -> webDriver.getCurrentUrl().equals(originalUrl));
    }

    @Test
    public void testOurAircraftDkc() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String originalUrl = driver.getCurrentUrl();

        // Find link within nav using XPath
            WebElement aircraftLink = driver.findElement(By.xpath("//button//span[contains(text(), 'Our Aircraft')]"));
        aircraftLink.click();

        WebElement okyLink = driver.findElement(By.xpath("//nav//a[contains(text(), 'VH-DKC - 2006 Cessna 182T')]"));
        okyLink.click();

        assertNotEquals(originalUrl, driver.getCurrentUrl());

        assertTrue(driver.getPageSource().contains(
                "DKC is a low time 2006 aircraft in great shape."));

        // Go back and wait for the navigation to complete
        driver.navigate().back();
        wait.until(webDriver -> webDriver.getCurrentUrl().equals(originalUrl));
    }

    @Test
    public void testNews() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String originalUrl = driver.getCurrentUrl();

        WebElement newsLink = driver.findElement(By.xpath("//nav//a[contains(text(), 'News')]"));
        newsLink.click();

        wait.until(webDriver -> !webDriver.getCurrentUrl().equals(originalUrl));

        assertNotEquals(originalUrl, driver.getCurrentUrl());

        assertTrue(driver.getPageSource().contains(
                "Great turnout for the October wash &amp; polish. Wash great"));

        driver.navigate().back();
        wait.until(webDriver -> webDriver.getCurrentUrl().equals(originalUrl));
    }

    @Test
    public void testFaq() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String originalUrl = driver.getCurrentUrl();

        WebElement faqLink = driver.findElement(By.xpath("//nav//a[contains(text(), 'FAQ')]"));
        faqLink.click();

        wait.until(webDriver -> !webDriver.getCurrentUrl().equals(originalUrl));

        assertNotEquals(originalUrl, driver.getCurrentUrl());

        assertTrue(driver.getPageSource().contains(
                "n short, MAG will never sell or provide your details to anyone. We are a non-profit organisation and have no interest in monetising mailing lists. We only collect your details in order to provide membership information and group updates."));

        driver.navigate().back();
        wait.until(webDriver -> webDriver.getCurrentUrl().equals(originalUrl));
    }

    @Test
    public void testContact() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String originalUrl = driver.getCurrentUrl();

        WebElement contactLink = driver.findElement(By.xpath("//nav//a[contains(text(), 'Contact')]"));
        contactLink.click();

        wait.until(webDriver -> !webDriver.getCurrentUrl().equals(originalUrl));

        assertNotEquals(originalUrl, driver.getCurrentUrl());

        assertTrue(driver.getPageSource().contains(
                "ABN 62 004 589 557"));

        // Go back and wait for the navigation to complete
        driver.navigate().back();
        wait.until(webDriver -> webDriver.getCurrentUrl().equals(originalUrl));
    }

    @Test
    public void testPrivacy() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String originalUrl = driver.getCurrentUrl();

        WebElement contactLink = driver.findElement(By.xpath("//nav//a[contains(text(), 'Privacy')]"));
        contactLink.click();

        wait.until(webDriver -> !webDriver.getCurrentUrl().equals(originalUrl));

        assertNotEquals(originalUrl, driver.getCurrentUrl());

        assertTrue(driver.getPageSource().contains(
                "This Policy may change from time to time and is available on our website at"));

        // Go back and wait for the navigation to complete
        driver.navigate().back();
        wait.until(webDriver -> webDriver.getCurrentUrl().equals(originalUrl));
    }

    @After
    public void tearDown() {
        // Optionally, you can add code here to reset the state if needed
    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}