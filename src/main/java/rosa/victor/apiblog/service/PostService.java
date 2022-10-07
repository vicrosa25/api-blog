package rosa.victor.apiblog.service;

import rosa.victor.apiblog.dtos.PostDto;
import rosa.victor.apiblog.dtos.PostResponse;

public interface PostService {

  PostDto createPost(PostDto postDto);

  PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

  PostDto getPostById(Long id);

  PostDto updatePost(PostDto postDto, Long id);

  void deletePostById(Long id);

}
