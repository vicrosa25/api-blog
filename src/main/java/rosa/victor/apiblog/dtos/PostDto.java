package rosa.victor.apiblog.dtos;

import lombok.Data;

@Data
public class PostDto {
  private Long Id;
  private String title;
  private String description;
  private String content;
}
