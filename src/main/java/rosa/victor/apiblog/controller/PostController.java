package rosa.victor.apiblog.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rosa.victor.apiblog.dtos.PostDto;
import rosa.victor.apiblog.dtos.PostResponse;
import rosa.victor.apiblog.service.PostService;
import rosa.victor.apiblog.utils.Constants;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  private PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping
  public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
    return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
  }

  @GetMapping
  public PostResponse getAllPost(
      @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
      @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false) String sortBy,
      @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
    return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(postService.getPostById(id));
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") Long id, @RequestBody PostDto postDto) {
    return ResponseEntity.ok(postService.updatePost(postDto, id));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
    postService.deletePostById(id);
    return new ResponseEntity<String>("Post Deleted successfully", HttpStatus.OK);
  }

}
