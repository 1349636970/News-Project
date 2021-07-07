package US.SummerChallenge.NewsProject.model.enums;

import lombok.Getter;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/30 1:01
 **/
@Getter
public enum NewsDataSources {
    CCTV("https://api.cntv.cn/NewArticle/getArticleListByPageId?serviceId=pcenglish&id=PAGE1394789601117162&n=20&t=jsonp&cb=Callback");



    private final String link;
    private int pageNumber;
    NewsDataSources(String link) {
        this.link = link;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getLink() {
        return this.link+"&p="+String.valueOf(pageNumber);
    }
}
