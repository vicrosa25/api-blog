package rosa.victor.apiblog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rosa.victor.apiblog.dtos.CommentDto;
import rosa.victor.apiblog.service.CommentService;

@Controller
@RequestMapping(value = "/api")
public class CommentController {

  CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping(value = "/posts/{postId}/comments")
  public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") long postId,
      @RequestBody CommentDto commentDto) {
    return new ResponseEntity<CommentDto>(commentService.creaeteComment(postId, commentDto), HttpStatus.CREATED);
  }
}
