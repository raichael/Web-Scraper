import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    public class WebsiteScraper {

    File f1 = new File("D:\\download.txt");
    private List arr = new ArrayList();
    private List arr1 = new ArrayList();
    private static JTextField url = new JTextField(30);
    private static JButton button = new JButton("Start");
    public static void main(String[] args)throws Exception {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.add(url);
        panel.add(button);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setLayout(null);
        button.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                WebsiteScraper websiteScraper = new WebsiteScraper();
                try {
                    websiteScraper.get();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void get() throws IOException {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\User\\Desktop\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(url.getText());
        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
//        System.out.println(links.size());
        arr.add(links.get(1).getText());
        FileWriter fw;
        fw = new FileWriter(f1);
        if(f1.exists())
        {
           System.out.println("file exists ");
        }else{
            System.out.println("create a file...!!!");
        }
        for (int i = 0; i <= links.size() - 1; i = i + 1) {
            if (!arr.contains(links.get(i).getText())){
                arr.add(links.get(i).getText());
            }
           else
               {
                   System.out.println("duplicate found...");
               }
            }
        for(int i=0;i<arr.size();i++)
        {
            String temp=arr.get(i).toString();
            System.out.println(" "+temp);
            fw.write(temp);
            fw.write("\n");
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(arr);
        System.out.println("//////////////////////////////////////////////////");

            java.util.List<WebElement> allImages = driver.findElements(By.tagName("img"));
            for (WebElement imageFromList : allImages) {
                String ImageUrl = imageFromList.getAttribute("src");
                arr1.add(ImageUrl);
                System.out.println(ImageUrl);
            }
            for (int j=0;j<arr1.size();j++){
                String temp1 =arr1.get(j).toString();
                fw.write(temp1);
                fw.write("\n");
            }
        fw.close();
    }
    }
