package US.SummerChallenge.NewsProject.model.enums;

import lombok.Getter;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/30 1:01
 **/
@Getter
public enum NewsDataSources {
    CCTV("https://api.cntv.cn/NewArticle/getArticleListByPageId?serviceId=pcenglish&id=PAGE1394789601117162&n=20&t=jsonp&cb=Callback"),
    CBS("https://www.cbsnews.com/world/")
    ;

    private final String link;
    NewsDataSources(String link) {
        this.link = link;
    }


    public String getLink(int pageNumber) {
        return this.link+"&p=" + pageNumber;
    }

    public String getLink() {
        return this.link;
    }
}
