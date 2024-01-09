package valera.gord.magnusmovieproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Long id;
    private String textReview;

    @NotNull
    private Integer rating;

    @ManyToOne
    private User user;


    @ManyToOne
    @JoinColumn(name = "movie_id",nullable = false)
    private Movie movie;
    }

