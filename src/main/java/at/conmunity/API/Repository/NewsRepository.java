package at.conmunity.API.Repository;

import at.conmunity.API.Model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByOrderByCreatedDesc();
}
