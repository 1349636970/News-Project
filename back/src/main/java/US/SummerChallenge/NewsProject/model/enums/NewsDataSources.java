package US.SummerChallenge.NewsProject.model.enums;

import lombok.Getter;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/30 1:01
 **/
@Getter
public enum NewsDataSources {
    NBCNEWS()



    private final String link;
    NewsDataSources(String link) {
        this.link = link;
    }
}
