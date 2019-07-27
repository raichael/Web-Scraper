import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.sql.Driver;
import java.util.List;

    public class WebsiteScraper {
    private static JTextField url = new JTextField(30);
    private static JButton button = new JButton("Start");
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.add(url);
        panel.add(button);
        panel.setSize(400, 400);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setLayout(null);
        button.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                WebsiteScraper websiteScraper = new WebsiteScraper();
                websiteScraper.get();
            }
        });
    }
    public void get() {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\User\\Desktop\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(url.getText());

        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println(links.size());

        for (int i = 1; i <= links.size() - 1; i = i + 1) {

            System.out.println(links.get(i).getText());

        }
    }
    }
