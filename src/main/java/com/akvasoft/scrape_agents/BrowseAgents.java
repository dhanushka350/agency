package com.akvasoft.scrape_agents;

import com.ImageTypers.ImageTypersAPI;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Controller
public class BrowseAgents implements InitializingBean {
    private static FirefoxDriver driver = null;
    private static String url[] = {"https://www.independentagent.com/contactus/pages/memberdirectory/FindAnAgent.aspx?sid=MnwxMS8xOS8yMDE4IDEyOjE0OjUyIFBNfHNzby5paWFiYS5uZXR8fHBhbDB4YW10c3h5YnpjMXFzcWZ5Y3ZjeHwxOTIuMTY4LjExNS44NXxodHRwczovL3Nzby5paWFiYS5uZXQvdmVyaWZ5LmFzcHg%2Fc2lkPU1Yd3hNUzh4T1M4eU1ERTRJREV5T2pFME9qVXhJRkJOZkhkM2R5NXBibVJsY0dWdVpHVnVkR0ZuWlc1MExtTnZiWHg4ZEdaa2RXUmhjR3hzYTNGck1XNWhjWGQ2Y25KcFlYTjRmREU1TWk0eE5qZ3VNVEUxTGpnMWZHaDBkSEJ6T2k4dmQzZDNMbWx1WkdWd1pXNWtaVzUwWVdkbGJuUXVZMjl0TDJOdmJuUmhZM1IxY3k5d1lXZGxjeTl0WlcxaVpYSmthWEpsWTNSdmNua3ZSbWx1WkVGdVFXZGxiblF1WVhOd2VIeEdZV3h6Wlh3d2ZIeDhmSHg4LVBURXVRU1RtZThvPXxGYWxzZXwwfHx8fHx8-R4nXA7NtfC0%3D&fbclid=IwAR3hpCblYx1dwcv7BkiLcXCEX8M2Zvb4c4lCwDp1Cb0NqwczEo62wmWKayI"};
    private static String codes[] = {"AGENTS"};
    private static HashMap<String, String> handlers = new HashMap<>();
    boolean newRow = true;
    String lastCode = "";
    @Autowired
    private Repo repo;
    @Autowired
    private CodeRepo codeRepo;

    public void initialise() throws Exception {

        System.setProperty("webdriver.gecko.driver", "/var/lib/tomcat8/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);

        driver = new FirefoxDriver(options);

        for (int i = 0; i < url.length - 1; i++) {
            driver.executeScript("window.open()");
        }

        ArrayList<String> windowsHandles = new ArrayList<>(driver.getWindowHandles());

        for (int i = 0; i < url.length; i++) {
            handlers.put(codes[i], windowsHandles.get(i));
        }

        String codes[] = {"99546", "99571", "99615", "99551", "99552", "99553", "99554", "99555", "99720", "99786", "99721",
                "99721", "99505", "99556", "99501", "99502", "99503", "99504", "99505", "99506", "99507", "99508", "99509",
                "99510", "99511", "99513", "99514", "99515", "99516", "99517", "99518", "99519", "99520", "99521", "99522",
                "99523", "99524", "99529", "99530", "99540", "99599", "99695", "99744", "99820", "99557", "99558", "99722",
                "99547", "99559", "99791", "99821", "99711", "99723", "99734", "99759", "99789", "99791", "99724", "99559",
                "99637", "99679", "99680", "99690", "99726", "99726", "99652", "99785", "99727", "99729", "99730", "99788",
                "99561", "99574", "99563", "99674", "99732", "99548", "99564", "99565", "99548", "99615", "99566", "99557",
                "99567", "99733", "99568", "99569", "99704", "99918", "99546", "99571", "99701", "99572", "99572", "99573",
                "99574", "99677", "99921", "99575", "99736", "99731", "99737", "99731", "99737", "99755", "99755", "99576",
                "99762", "99737", "99824", "99692", "99738", "99577", "99901", "99950", "99578", "99579", "99702", "99580",
                "99825", "99739", "99506", "99581", "99603", "99725", "99701", "99702", "99705", "99706", "99707", "99708",
                "99709", "99710", "99711", "99712", "99714", "99716", "99767", "99775", "99790", "99583", "99731", "99505",
                "99703", "99740", "99788", "99603", "99586", "99741", "99742", "99587", "99693", "99588", "99762", "99589",
                "99590", "99586", "99826", "99827", "99603", "99743", "99755", "99602", "99603", "99829", "99604", "99605",
                "99694", "99745", "99746", "99922", "99923", "99613", "99606", "99647", "99540", "99801", "99802", "99803",
                "99811", "99812", "99821", "99824", "99850", "99830", "99747", "99607", "99748", "99608", "99901", "99950",
                "99609", "99610", "99611", "99635", "99901", "99903", "99918", "99919", "99950", "99749", "99612", "99549",
                "99613", "99614", "99750", "99925", "99751", "99615", "99619", "99697", "99606", "99606", "99576", "99545",
                "99620", "99752", "99753", "99754", "99621", "99545", "99622", "99757", "99624", "99625", "99762", "99626",
                "99756", "99756", "99628", "99585", "99627", "99675", "99691", "99627", "99629", "99630", "99780", "99926",
                "99903", "99586", "99758", "99631", "99632", "99633", "99603", "99634", "99559", "99901", "99571", "99704"};
        boolean firstAttempt = true;
        boolean done = false;
        int count = 1;
        List<Code> codeList = codeRepo.findAllByCodeBetween("12020", "11565");
        System.out.println(codeList.size());
        Content last = repo.findTopByOrderByIdDesc();
        if (last != null) {
            lastCode = last.getCode();
        }

        System.err.println("last code = " + lastCode);
        while (!done) {
            for (String code : codes) {
                System.out.println("current code " + code + " count " + count);

                if (firstAttempt && code.equalsIgnoreCase(lastCode) || code.equalsIgnoreCase("")) {
                    //scrapeContent(code);
                    lastCode = code;
                    System.err.println("skipping code that already scraped..");
                    firstAttempt = false;
                    continue;
                }
                scrapeContent(code);
                lastCode = code;
                count++;

            }
            done = true;
        }
    }


    private void scrapeContent(String code) throws Exception {
        driver.get("https://www.independentagent.com/contactus/pages/memberdirectory/FindAnAgent.aspx?sid=MnwxMS8xOS8yMDE4IDEyOjE0OjUyIFBNfHNzby5paWFiYS5uZXR8fHBhbDB4YW10c3h5YnpjMXFzcWZ5Y3ZjeHwxOTIuMTY4LjExNS44NXxodHRwczovL3Nzby5paWFiYS5uZXQvdmVyaWZ5LmFzcHg%2Fc2lkPU1Yd3hNUzh4T1M4eU1ERTRJREV5T2pFME9qVXhJRkJOZkhkM2R5NXBibVJsY0dWdVpHVnVkR0ZuWlc1MExtTnZiWHg4ZEdaa2RXUmhjR3hzYTNGck1XNWhjWGQ2Y25KcFlYTjRmREU1TWk0eE5qZ3VNVEUxTGpnMWZHaDBkSEJ6T2k4dmQzZDNMbWx1WkdWd1pXNWtaVzUwWVdkbGJuUXVZMjl0TDJOdmJuUmhZM1IxY3k5d1lXZGxjeTl0WlcxaVpYSmthWEpsWTNSdmNua3ZSbWx1WkVGdVFXZGxiblF1WVhOd2VIeEdZV3h6Wlh3d2ZIeDhmSHg4LVBURXVRU1RtZThvPXxGYWxzZXwwfHx8fHx8-R4nXA7NtfC0%3D&fbclid=IwAR3hpCblYx1dwcv7BkiLcXCEX8M2Zvb4c4lCwDp1Cb0NqwczEo62wmWKayI");
        WebElement textBox = driver.findElementByXPath("//*[@id=\"iaQuoteByZip\"]");
        WebElement okButton = driver.findElementByXPath("//*[@id=\"iaFAALink\"]");
        textBox.sendKeys(new String[]{code});
        okButton.click();
        Content content = new Content();
        content.setCode(code);
        content.setCompany("--");
        content.setEmail("--");
        content.setContact("--");
        content.setAddress("--");

        if (resolveCaptcha()) {
            repo.save(content);
            scrapeInnerPage(code);
        }
    }

    private void scrapeInnerPage(String code) throws InterruptedException {
        List<Content> list = new ArrayList<>();
        Content content = null;
        Thread.sleep(2000);
        try {
            for (WebElement item : driver.findElement(By.xpath("//*[@id=\"iaFaaLstView\"]")).findElements(By.xpath("./*"))) {
                content = new Content();
                List<WebElement> values = item.findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
                content.setCompany(values.get(0).getAttribute("innerText"));
                content.setCode(code);
                content.setAddress(values.get(1).findElements(By.tagName("td")).get(1).getAttribute("innerText").split("P :")[0].replace("\n", ","));
                content.setContact(values.get(1).findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).getAttribute("innerText").split("\n")[0].replace("P :", ""));
                try {
                    content.setEmail(values.get(1).findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).findElement(By.tagName("a")).getAttribute("innerText"));
                } catch (NoSuchElementException e) {
                    content.setEmail("N/A");
                }
                System.err.println("Code == " + code);
                System.err.println("Company == " + content.getCompany());
                System.err.println("Address == " + content.getAddress());
                System.err.println("Contact == " + content.getContact());
                System.err.println("Email == " + content.getEmail());
                repo.save(content);
            }
        } catch (NoSuchElementException e) {
            System.err.println("AGENTS NOT FOUND FOR THIS CODE");
        }
    }

    private boolean resolveCaptcha() throws Exception {
        String access_token = "6FCD4FA636F442C7A33A4963BDBA9122";
        ImageTypersAPI c = new ImageTypersAPI(access_token);
        String balance = c.account_balance();
        String cptchaID = "";
        System.out.println(String.format("Balance: %s", balance));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        boolean isCaptchaWrong = true;
        while (isCaptchaWrong) {
            try {
                WebElement ele = driver.findElementByXPath("//*[@id=\"ctl00_ctl59_g_e5a49396_daf3_4163_a8e9_5f6827aa63e3_ctl00_rcRobotCheck_CaptchaImageUP\"]");

                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                BufferedImage fullImg = null;

                fullImg = ImageIO.read(screenshot);

                Point point = ele.getLocation();

                int eleWidth = ele.getSize().getWidth();
                int eleHeight = ele.getSize().getHeight();

                BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
                ImageIO.write(eleScreenshot, "png", screenshot);

                File screenshotLocation = new File("/var/lib/tomcat8/history/doc/captcha.png");
                FileUtils.copyFile(screenshot, screenshotLocation);

                System.out.println("Waiting for captcha to be solved ...");
                String resp = c.solve_captcha("/var/lib/tomcat8/history/doc/captcha.png", true);
                cptchaID = c.captcha_id();

                System.out.println(String.format("Captcha text: %s", resp));

                WebElement captcha = driver.findElementByXPath("//*[@id=\"ctl00_ctl59_g_e5a49396_daf3_4163_a8e9_5f6827aa63e3_ctl00_rcRobotCheck_CaptchaTextBox\"]");
                System.out.println(captcha.getAttribute("id") + "|||||||||||||||||||||||||||||||||||||||||||||||||");
                captcha.clear();
                captcha.sendKeys(resp);

                WebElement submit = driver.findElementByXPath("//*[@id=\"ctl00_ctl59_g_e5a49396_daf3_4163_a8e9_5f6827aa63e3_ctl00_btnRobotCheck\"]");
                submit.click();

                if (driver.getCurrentUrl() != "https://www.independentagent.com/contactus/pages/memberdirectory/FindAnAgent.aspx?sid=MnwxMS8xOS8yMDE4IDEyOjE0OjUyIFBNfHNzby5paWFiYS5uZXR8fHBhbDB4YW10c3h5YnpjMXFzcWZ5Y3ZjeHwxOTIuMTY4LjExNS44NXxodHRwczovL3Nzby5paWFiYS5uZXQvdmVyaWZ5LmFzcHg%2Fc2lkPU1Yd3hNUzh4T1M4eU1ERTRJREV5T2pFME9qVXhJRkJOZkhkM2R5NXBibVJsY0dWdVpHVnVkR0ZuWlc1MExtTnZiWHg4ZEdaa2RXUmhjR3hzYTNGck1XNWhjWGQ2Y25KcFlYTjRmREU1TWk0eE5qZ3VNVEUxTGpnMWZHaDBkSEJ6T2k4dmQzZDNMbWx1WkdWd1pXNWtaVzUwWVdkbGJuUXVZMjl0TDJOdmJuUmhZM1IxY3k5d1lXZGxjeTl0WlcxaVpYSmthWEpsWTNSdmNua3ZSbWx1WkVGdVFXZGxiblF1WVhOd2VIeEdZV3h6Wlh3d2ZIeDhmSHg4LVBURXVRU1RtZThvPXxGYWxzZXwwfHx8fHx8-R4nXA7NtfC0%3D&fbclid=IwAR3hpCblYx1dwcv7BkiLcXCEX8M2Zvb4c4lCwDp1Cb0NqwczEo62wmWKayI") {
                    isCaptchaWrong = false;
                    return true;
                } else {
                    c.set_captcha_bad(cptchaID);
                }
            } catch (Exception e) {
                System.err.println("image timeout exception in captcha resolve.. retrying");
            }
        }
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.initialise();
    }
}
