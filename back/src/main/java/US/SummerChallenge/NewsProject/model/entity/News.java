package US.SummerChallenge.NewsProject.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 0:44
 **/
@Entity
@Table(name = "news")
@Getter
@Setter
public class News {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "News Title")
    private String newsTitle;
    @Column(name = "News Sources")
    private String newsSources;
    @Column(name = "News Tag")
    private String newsTag;
    @Column(name = "News Image")
    private String newsImage;
    @Column(name = "News Summary")
    private String newsSummary;
}
