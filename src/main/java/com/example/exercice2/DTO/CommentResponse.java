package exercice2.DTO;

import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String message;
    private Long articleId;
}