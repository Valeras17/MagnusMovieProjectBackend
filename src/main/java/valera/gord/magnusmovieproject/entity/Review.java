package valera.gord.magnusmovieproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;

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
    private String rating;

    @ManyToOne
    private User user;


    @ManyToOne
    @JoinColumn(name = "movie_id",nullable = false)
    private Movie movie;
    }

