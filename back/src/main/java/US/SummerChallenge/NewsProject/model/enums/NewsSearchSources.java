package US.SummerChallenge.NewsProject.model.enums;


/**
 * @Author: Zhenyu Lin
 * @Date: 2021/7/21 7:58
 **/
public enum NewsSearchSources {
    XINHUA("http://search.news.cn/getNews"),
    CBS("https://api.queryly.com/json.aspx"),
    THEHIMALAYANTIMES("https://thehimalayantimes.com/search?query=")
    ;
    private final String link;
    NewsSearchSources(String link) {
        this.link = link;
    }

    public String getLink(String searchKeyWord) {
        return switch (this.name()) {
            case "XINHUA" -> this.link + "?keyword="+searchKeyWord+"&curPage=1&sortField=0&searchFields=1&lang=en";
            case "CBS" -> this.link + "?queryly_key=4690eece66c6499f&batchsize=15&query="+searchKeyWord+"&groups=live:0_4_2&showfaceted=true";
            case "THEHIMALAYANTIMES"-> this.link+searchKeyWord;
            default -> throw new IllegalStateException("Unexpected value: " + this.name());
        };

    }
}
