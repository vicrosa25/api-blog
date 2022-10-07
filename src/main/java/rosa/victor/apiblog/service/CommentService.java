package rosa.victor.apiblog.service;

import rosa.victor.apiblog.dtos.CommentDto;

public interface CommentService {
  CommentDto creaeteComment(long postId, CommentDto commentDto);
}
