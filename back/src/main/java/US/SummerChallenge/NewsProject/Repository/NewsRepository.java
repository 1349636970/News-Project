package US.SummerChallenge.NewsProject.Repository;

import US.SummerChallenge.NewsProject.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 0:58
 **/
@Repository
public interface NewsRepository extends JpaRepository<News,Long>, JpaSpecificationExecutor<News> {
}
