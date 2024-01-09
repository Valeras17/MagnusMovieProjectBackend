package valera.gord.magnusmovieproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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
    private String year;
    @NotNull
    private String url;
    @NotNull
    private String poster;
    @NotNull
    private String genre;
    @NotNull
    private String backdropScreen;


    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

}