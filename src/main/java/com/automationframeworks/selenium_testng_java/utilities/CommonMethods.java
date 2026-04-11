package com.automationframeworks.selenium_testng_java.utilities;

import java.io.FileInputStream;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.regex.Pattern;

import java.util.stream.Collectors;

public class CommonMethods extends Base {

    private WebDriver driver;

    private Actions actions;

    private WebDriverWait wait;

    public static final Logger logger = LogManager.getLogger(CommonMethods.class);

    private JavascriptExecutor javascriptExecutor;

    private final TestUserProvider userProvider = new TestUserProvider();
    private final Duration DEFAULT_WAIT_DURATION = Duration.ofSeconds(60);

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        this.javascriptExecutor = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }
    public void loginToSauceLabs(JSONObject userCredentials) {
    	
    	
    }
    

    public String generateRandomStringWithNumber(int length) {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < length; i++) {
        result.append(characters.charAt(random.nextInt(characters.length())));
        }
    return result.toString();
    }


    public int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }

        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static int generateRandomNumberWithDigits(int numberOfDigits) {
        if (numberOfDigits < 1) {
            throw new IllegalArgumentException("Number of digits must be >= 1");
        }

        Random random = new Random();
        int lowerBound = (int) Math.pow(10, numberOfDigits - 1); // Minimum value for the required digits
        int upperBound = (int) Math.pow(10, numberOfDigits) - 1; // Maximum value for the required digits
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    public String readExcel(int rownum, int columnnum) throws IOException {
        System.out.println("Reading data from Excel file");
        //FileInputStream fis = new FileInputStream("./TestData\\LoginData.xlsx");
        FileInputStream fis = new FileInputStream("./TestData/Login Data.xlsx");
        XSSFWorkbook srcBook = new XSSFWorkbook(fis);
        XSSFSheet sourceSheet = srcBook.getSheetAt(0);
        XSSFRow sourceRow = sourceSheet.getRow(rownum);
        XSSFCell cell = sourceRow.getCell(columnnum);
        return cell.getStringCellValue();
    }
}
