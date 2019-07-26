import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
    public class WebsiteScraper extends JTabbedPane
    {
        Document doc;
        JScrollPane spane;
        WebsiteScraper()
        {
            try
            {
                doc=Jsoup.connect("-------").get();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            getLinks();

        }
        public void getLinks()
        {
            Elements links=doc.getElementsByTag("a");
            JPanel linkpanel=new JPanel();
            linkpanel.setLayout(new GridLayout(links.size(),1));
            for(Element link:links)
            {
                String l=link.attr("href");
                if(l.length()>0)
                {
                    if(l.length()<4)
                    {
                        l=doc.baseUri()+l.substring(1);
                    }
                    else if(!l.substring(0,4).equals("http"))
                    {
                        l=doc.baseUri()+l.substring(1);
                    }

                }
            }
            spane=new JScrollPane(linkpanel);
            spane.setPreferredSize(new Dimension(350,350));
        }
        public static void main(String[] args)
        {
            JFrame frame=new JFrame("Website Scraper");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setSize(400,400);
        }
    }
