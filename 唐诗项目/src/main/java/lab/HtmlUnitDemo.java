package lab;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HtmlUnitDemo {
    public static void main(String[] args) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        HtmlPage page = webClient.getPage("https://so.gushiwen.org/gushi/tangshi.aspx");
        System.out.println(page);
//

        HtmlElement body = page.getBody();
        List<HtmlElement> elements = body.getElementsByAttribute("div", "class", "typecont");
        for (HtmlElement element : elements) {
            System.out.println(element);
        }
        HtmlElement divElement = elements.get(0);
        List<HtmlElement> aElements = divElement.getElementsByAttribute("a","target","_blank");

        for (HtmlElement element : aElements) {
            System.out.println(element);
        }
        System.out.println(aElements.get(0).getAttribute("href"));
    }
}
