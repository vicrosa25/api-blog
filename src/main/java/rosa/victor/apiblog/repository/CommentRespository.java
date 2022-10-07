package rosa.victor.apiblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rosa.victor.apiblog.model.Comment;

public interface CommentRespository extends JpaRepository<Comment, Long>{
  
}
