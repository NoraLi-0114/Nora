package com.TestLink;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyLinks {
	

	@Test
	public void test() throws IOException {
		System.setProperty("webdriver.chrome.driver",
			     "C:\\NoraLi\\Nora\\chromedriver_win32_79\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.legion.co/");
		List<WebElement> list = driver.findElements(By.tagName("a"));
		for(WebElement webElement:list){
			System.out.println(webElement.getAttribute("href"));
			String url = webElement.getAttribute("href");
			if(url.startsWith("http")&&!url.endsWith("#")){
				URL u = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) u.openConnection();
				connection.connect();
				//System.out.println(connection.getResponseCode());
				if (connection.getResponseCode() >= 100 && connection.getResponseCode() < 400)
				{
					System.out.println("Access " + url + " successfully.");
				}
				else {
					System.out.println("Access " + url + " failed with response code: " + connection.getResponseCode());
				}
				}
			}
		driver.close();
		}
	}
