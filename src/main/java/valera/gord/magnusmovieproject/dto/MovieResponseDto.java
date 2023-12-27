package valera.gord.magnusmovieproject.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private LocalDate releaseDate;
    private String genre;
    private Integer duration; // in minutes
    private String director;
    private String cast;


}
