package rosa.victor.apiblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import rosa.victor.apiblog.dtos.PostDto;
import rosa.victor.apiblog.dtos.PostResponse;
import rosa.victor.apiblog.exception.ResourceNotFoundException;
import rosa.victor.apiblog.model.Post;
import rosa.victor.apiblog.repository.PostRepository;
import rosa.victor.apiblog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public PostDto createPost(PostDto postDto) {

    // Convert DTO to entity
    Post post = mapToPost(postDto);

    // Save to the Database
    Post savedPost = postRepository.save(post);

    // Convert Entity to Dto
    PostDto postResponse = mapToDto(savedPost);

    return postResponse;
  }

  @Override
  public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

    // 0. Create the sort object with the right direcction
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
        : Sort.by(sortBy).descending();

    // 1. Create a Pageable instance
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    Page<Post> pages = postRepository.findAll(pageable);

    // 2. Get content from page object
    List<Post> posts = pages.getContent();

    // 3. Transform posts to a list of PostDto
    List<PostDto> content = posts.stream()
        .map(post -> mapToDto(post))
        .collect(Collectors.toList());

    // 4. Create and return the PostResponse object
    PostResponse postResponse = new PostResponse();
    postResponse.setContent(content);
    postResponse.setPageNo(pages.getNumber());
    postResponse.setPageSize(pages.getSize());
    postResponse.setTotalElements(pages.getTotalElements());
    postResponse.setTotalPages(pages.getTotalPages());
    postResponse.setLast(pages.isLast());

    return postResponse;
  }

  @Override
  public PostDto getPostById(Long id) {
    PostDto postDto = mapToDto(
        postRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)));
    return postDto;
  }

  @Override
  public PostDto updatePost(PostDto postDto, Long id) {
    Post post = postRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", id));
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());

    return mapToDto(post);
  }

  @Override
  public void deletePostById(Long id) {
    Post post = postRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", id));

    postRepository.delete(post);
  }

  // Convert PostDto to Post
  private Post mapToPost(PostDto postDto) {
    Post post = new Post();
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());
    return post;
  }

  // Convert Post to PostDto
  private PostDto mapToDto(Post post) {
    PostDto postDto = new PostDto();
    postDto.setId(post.getId());
    postDto.setTitle(post.getTitle());
    postDto.setDescription(post.getDescription());
    postDto.setContent(post.getContent());
    return postDto;
  }

}
