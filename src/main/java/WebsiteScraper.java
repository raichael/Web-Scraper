import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class WebsiteScraper {
    private File filepath = new File("D:\\download.txt");
    private static JTextField url = new JTextField(30);
    private static JButton start_button = new JButton("Start");
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.add(url);
        panel.add(start_button);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setLayout(null);
        start_button.addActionListener(new AbstractAction() {
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
    private void get() throws IOException {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\User\\Desktop\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(url.getText());
        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
        FileWriter fw;
        fw = new FileWriter(filepath);
        if(filepath.exists())
        {
           System.out.println("file exists ");
        }else{
           JOptionPane.showMessageDialog(null,"Create folder...");
        }
        java.util.HashMap<String,String> map= new HashMap<String, String>();
        map.put(links.get(1).getText(),links.get(1).getText());
        for (int i = 2; i <= links.size() - 1; i = i + 1) {
            if(links.get(i).getText().isEmpty()|| (links.get(i).getText()==null))
            {
                continue;
            }

            if(map.containsKey(links.get(i).getText())) {
                JOptionPane.showMessageDialog(null,"duplicates found...");

            }else {
                map.put(links.get(i).getText(),links.get(i).getText());
            }
        }
        for (Map.Entry m :map.entrySet()) {
            fw.write((String) m.getKey());
            fw.write("\n");
        }

        HashMap <String, String> map1 = new HashMap<String, String>();
            java.util.List<WebElement> allImages = driver.findElements(By.tagName("img"));
            for (WebElement imageFromList : allImages) {
                String ImageUrl = imageFromList.getAttribute("src");
                if (ImageUrl==null){
                    continue;
                }
                if (!map1.containsKey(ImageUrl)){
                    map1.put(ImageUrl,ImageUrl);
                }else {
                    JOptionPane.showMessageDialog(null,"duplicate images.....");
                }

            }
        for (Map.Entry n:map1.entrySet()) {
            fw.write((String) n.getKey());
            fw.write("\n");
        }
        fw.close();

    }
}