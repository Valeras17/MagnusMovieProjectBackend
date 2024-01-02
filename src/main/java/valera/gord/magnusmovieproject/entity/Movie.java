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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String title;
    @NotNull
    @Column(name = "overview")
    private String description;
    @NotNull
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @NotNull
    private String genre;
    @NotNull
    private Integer duration;
    @NotNull
    private String director;
    @NotNull
    @Column(name =  "poster_path")
    private String poster;

    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

}