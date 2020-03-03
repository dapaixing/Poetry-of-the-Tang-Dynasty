//package 单线程版;
//
//import com.gargoylesoftware.htmlunit.BrowserVersion;
//import com.gargoylesoftware.htmlunit.Page;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.DomNodeList;
//import com.gargoylesoftware.htmlunit.html.DomText;
//import com.gargoylesoftware.htmlunit.html.HtmlElement;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//
//import java.sql.Connection;
//
//import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
//import org.ansj.domain.Term;
//import org.ansj.splitWord.analysis.NlpAnalysis;
//
//import java.io.IOException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SingleThreadCatch02 {
//    public static void main(String[] args) throws IOException, SQLException, NoSuchAlgorithmException {
//        WebClient client = new WebClient(BrowserVersion.CHROME);
//        client.getOptions().setCssEnabled(false);
//        client.getOptions().setJavaScriptEnabled(false);
//
//        String leftUrl = "https://so.gushiwen.org";
//        String rightUrl = "/gushi/tangshi.aspx";
//
//        ArrayList<String> dateilUrlList = new ArrayList<>();
//        {
//            String url = leftUrl + rightUrl;
//            HtmlPage page = client.getPage(url);
//
//            List<HtmlElement> divs = page.getBody().getElementsByAttribute("div", "class", "typecont");
//            for (HtmlElement div : divs) {
//                DomNodeList<HtmlElement> as = div.getElementsByTagName("a");
//                for (HtmlElement a : as) {
//                    String dateilUrl = a.getAttribute("href");
//                    dateilUrlList.add(leftUrl + dateilUrl);
//                }
//            }
//
//        }
//        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
//        dataSource.setServerName("127.0.0.1");
//        dataSource.setPort(3306);
//        dataSource.setUser("root");
//        dataSource.setPassword("root");
//        dataSource.setDatabaseName("tangshi");
//        dataSource.setUseSSL(false);
//        dataSource.setCharacterEncoding("UTF-8");
//
//        Connection connection = dataSource.getConnection();
//        String sql = "INSERT INTO tangshi(sha256,dynasty,title,author,content,words)VALUES(?,?,?,?,?,?)";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//
//        for (String url : dateilUrlList)
//        {
//            HtmlPage page = client.getPage(url);
//            String xpath;
//            DomText domText;
//            xpath = "//div[@class = 'cont']/h1/text()";
//            domText = (DomText) page.getBody().getByXPath(xpath).get(0);
//            String title = domText.asText();
//
//            xpath = "//div[@class = 'cont']/p[@class = 'source']/a/text()";
//            domText = (DomText)page.getBody().getByXPath(xpath).get(0);
//            String dynasty = domText.asText();
//            domText = (DomText)page.getBody().getByXPath(xpath).get(1);
//            String author = domText.asText();
//
//            xpath = "//div[@class = 'cont']/div[@class = 'contson']";
//            HtmlPage htmlPage = (HtmlPage) page.getBody().getByXPath(xpath).get(0);
//            String content = htmlPage.getTextContent().trim();
//
//
//            String s = title + content;
//            messageDigest.update(s.getBytes());
//            byte[] digest = messageDigest.digest();
//            StringBuilder sha256 = new StringBuilder();
//            for (byte b : digest) {
//                sha256.append(String.format("%02x",b));
//            }
//
//            List<Term> termList = new ArrayList<>();
//            termList.addAll(NlpAnalysis.parse(title).getTerms());
//            termList.addAll(NlpAnalysis.parse(content).getTerms());
//            List<String> words = new ArrayList<>();
//            for (Term term : termList) {
//                if (term.getNatureStr().equals("w")){
//                    continue;
//                }
//                if (term.getNatureStr() == null){
//                    continue;
//                }
//                if (term.getRealName().length()<2){
//                    continue;
//                }
//                words.add(term.getRealName());
//            }
//            String insertWords = String.join("j", words);
//
//            statement.setString(1,sha256.toString());
//            statement.setString(2,dynasty);
//            statement.setString(3,title);
//            statement.setString(4,author);
//            statement.setString(5,content);
//            statement.setString(6,insertWords);
//            com.mysql.jdbc.PreparedStatement mysqlstatement = (com.mysql.jdbc.PreparedStatement)statement;
//            System.out.println(mysqlstatement.asSql());
//            statement.executeUpdate();
//        }
//    }
//}
//
