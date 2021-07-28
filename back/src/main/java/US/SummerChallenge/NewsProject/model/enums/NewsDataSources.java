package US.SummerChallenge.NewsProject.model.enums;

import lombok.Getter;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/30 1:01
 **/
@Getter
public enum NewsDataSources {
    CCTV("https://api.cntv.cn/NewArticle/getArticleListByPageId?serviceId=pcenglish&id=PAGE1394789601117162&n=20&t=jsonp&cb=Callback"),
    CBS("https://www.cbsnews.com/latest/rss/world"),
    XINHUA("http://www.xinhuanet.com/english/world/index.htm"),
    PEOPLE("http://en.people.cn/rss/90777.xml"),
    USTODAY("https://www.usatoday.com/news/world/"),
    NYTIME("https://rss.nytimes.com/services/xml/rss/nyt/World.xml"),
    THEHIMALAYANTIMES("https://thehimalayantimes.com/rssFeed/27"),
    KATHMANDUPOST("https://kathmandupost.com/national"),
    REPUBLICA("https://myrepublica.nagariknetwork.com/")
    ;

    private final String link;
    NewsDataSources(String link) {
        this.link = link;
    }


    public String getLink(int pageNumber) {
        return switch (this.name()) {
            case "CCTV" -> this.link+"&p=" + pageNumber;
            case "PEOPLE" -> this.link+"index"+pageNumber+".html";
            default -> throw new IllegalStateException("Unexpected value: " + this.name());
        };
    }

    public String getLink() {
        return switch (this.name()) {
            case "CCTV" -> this.link+"&p=1";
            case "PEOPLE" -> this.link+"index.html";
            default -> this.link;
        };
    }
}
