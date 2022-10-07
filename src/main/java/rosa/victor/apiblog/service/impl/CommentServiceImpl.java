package rosa.victor.apiblog.service.impl;

import org.springframework.stereotype.Service;

import rosa.victor.apiblog.dtos.CommentDto;
import rosa.victor.apiblog.exception.ResourceNotFoundException;
import rosa.victor.apiblog.model.Comment;
import rosa.victor.apiblog.model.Post;
import rosa.victor.apiblog.repository.CommentRespository;
import rosa.victor.apiblog.repository.PostRepository;
import rosa.victor.apiblog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

  private final CommentRespository commentRespository;
  private final PostRepository postRepository;

  public CommentServiceImpl(CommentRespository commentRespository, PostRepository postRepository) {
    this.commentRespository = commentRespository;
    this.postRepository = postRepository;
  }

  @Override
  public CommentDto creaeteComment(long postId, CommentDto commentDto) {

    Comment comment = mapToComment(commentDto);

    // Fetch post by postId
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Post", "id", postId));

    // Fill the relationship between comment and post
    comment.setPost(post);

    // Save the comment to DB
    Comment savedComment = commentRespository.save(comment);
    return mapToDto(savedComment);
  }

  // Convert PostDto to Post
  private Comment mapToComment(CommentDto commentDto) {
    Comment comment = new Comment();
    comment.setName(commentDto.getName());
    comment.setEmail(commentDto.getEmail());
    comment.setBody(commentDto.getBody());
    return comment;
  }

  // Convert Post to PostDto
  private CommentDto mapToDto(Comment comment) {
    CommentDto commentDto = new CommentDto();
    commentDto.setId(comment.getId());
    commentDto.setName(comment.getName());
    commentDto.setEmail(comment.getEmail());
    commentDto.setBody(comment.getBody());
    return commentDto;
  }

}
