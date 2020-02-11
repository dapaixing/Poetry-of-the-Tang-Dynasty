package lab;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

public class 详情页下载提取Demo {
    public static void main(String[] args) throws IOException {
        try (WebClient webClient = new WebClient(BrowserVersion.CHROME)){
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);
            String url = "https://so.gushiwen.org/shiwenv_45c396367f59.aspx";
            HtmlPage page = webClient.getPage(url);

            HtmlElement body = page.getBody();
//            List<HtmlElement> elements = body.getElementsByAttribute("div", "class", "contson");
//            for (HtmlElement element : elements) {
//                System.out.println(element);
//            }
//
//            System.out.println(elements.get(0).getTextContent().trim());
            //题目
            {
                String xpath = "//div[@class = 'cont']/h1/text()";
                Object o = body.getByXPath(xpath).get(0);
                DomText domText = (DomText)o;
                System.out.println(domText.asText());
            }
            //作者和年代
            {
                String xpath = "//div[@class = 'cont']/p[@class = 'source']/a/text()";
                Object o = body.getByXPath(xpath).get(0);
                DomText domText = (DomText)o;
                System.out.println(domText.asText());
                Object o1 = body.getByXPath(xpath).get(1);
                DomText domText1 = (DomText)o1;
                System.out.println(domText1.asText());
            }
            //正文
            {
                String xpath = "//div[@class = 'cont']/div[@class = 'contson']";
                Object o = body.getByXPath(xpath).get(0);
                HtmlElement element = (HtmlElement) o;
                System.out.println(element.getTextContent().trim());
            }
        }
    }
}
