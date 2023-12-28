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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String textReview;
    private String rating;


    // Отношение с пользователем
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Отношение с фильмом
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    }

