package valera.gord.magnusmovieproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String movie;
    private String reviweText;
    private Integer rating;


    // relationship: ManytoOne


}
