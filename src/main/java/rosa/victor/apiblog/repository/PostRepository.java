package rosa.victor.apiblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rosa.victor.apiblog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  
}
