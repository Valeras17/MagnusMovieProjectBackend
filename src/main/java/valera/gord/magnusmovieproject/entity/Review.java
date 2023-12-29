package valera.gord.magnusmovieproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private String textReview;
    private String rating;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "movie_id",nullable = false)
    private Movie movie;
    }

