package US.SummerChallenge.NewsProject.Repository;

import US.SummerChallenge.NewsProject.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 0:58
 **/
public interface NewsRepository extends JpaRepository<News,Integer> {
}
