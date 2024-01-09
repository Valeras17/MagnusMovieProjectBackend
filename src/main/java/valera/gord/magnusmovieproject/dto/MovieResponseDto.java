package valera.gord.magnusmovieproject.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String year;
    private String url;
    private String poster;
    private String genre;
    private String backdropScreen;


}
