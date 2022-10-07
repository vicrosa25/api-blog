package rosa.victor.apiblog.service;

import java.util.Set;

import rosa.victor.apiblog.dtos.CommentDto;

public interface CommentService {
  CommentDto creaeteComment(long postId, CommentDto commentDto);
  Set<CommentDto> getAllComments(long postId);
}
