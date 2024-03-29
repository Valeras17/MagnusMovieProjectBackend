package valera.gord.magnusmovieproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private LocalDate releaseDate;
    @NotNull
    private String genre;
    @NotNull
    private Integer duration;
    @NotNull
    private String director;
    @NotNull
    private String cast;

    // Отношение с отзывами
    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

}