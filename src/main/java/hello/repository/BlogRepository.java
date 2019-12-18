package hello.repository;

import hello.model.Blog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogRepository extends CrudRepository<Blog, Integer>{
    List<Blog> findAll();
}
