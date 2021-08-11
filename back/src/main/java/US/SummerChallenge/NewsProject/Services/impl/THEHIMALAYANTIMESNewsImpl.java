package US.SummerChallenge.NewsProject.Services.impl;

import US.SummerChallenge.NewsProject.Repository.NewsRepository;
import US.SummerChallenge.NewsProject.Services.base.AbstractNewsDataService;
import US.SummerChallenge.NewsProject.model.entity.News;
import US.SummerChallenge.NewsProject.model.enums.NewsDataSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/7/20 16:22
 **/
@Service
public class THEHIMALAYANTIMESNewsImpl extends AbstractNewsDataService {

    @Override
    public void dataFetch() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(NewsDataSources.THEHIMALAYANTIMES.getLink());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("item");
        for (int i = 0; i < nList.getLength(); i++) {
            Element nNode = (Element) nList.item(i);
            News news = new News();
            news.setNewsTitle(
                    nNode.getElementsByTagName("title").item(0).getTextContent()
//                    regex(nNode.getElementsByTagName("title").item(0).getTextContent(),"(?<=\\<\\!\\[CDATA\\[).+(?=\\]\\]\\>)")
            );
            news.setNewsSources(
                    nNode.getElementsByTagName("link").item(0).getTextContent()
//                    regex(nNode.getElementsByTagName("link").item(0).getTextContent(),"(?<=\\<\\!\\[CDATA\\[).+(?=\\]\\]\\>)"));
            );
            news.setNewsSummary(
                    nNode.getElementsByTagName("description").item(0).getTextContent()
            );
//                    regex(nNode.getElementsByTagName("description").item(0).getTextContent(),"(?<=\\<\\!\\[CDATA\\[).+(?=\\]\\]\\>)"));
            news.setTime(stringToData(nNode.getElementsByTagName("pubDate").item(0).getTextContent(), "EEE, dd MMM yyyy HH:mm:ss Z"));
            news.setNewsMedia(NewsDataSources.THEHIMALAYANTIMES.name());
            news.setCountry("NP");
            newsList.add(news);
        }
        newsRepository.saveAll(newsList);
        newsList.clear();
    }
    private String regex(String str,String pattern) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String result;
        if (m.find()) {
            result = m.group(0);
        } else {
            result = null;
        }
        return result;
    }
}
