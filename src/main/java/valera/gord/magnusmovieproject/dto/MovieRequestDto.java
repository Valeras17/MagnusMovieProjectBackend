package valera.gord.magnusmovieproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import valera.gord.magnusmovieproject.validators.UniqueTitle;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequestDto {
    @UniqueTitle
    @NotNull
    @Size(min = 2,max = 100)
    private String title;
    @NotNull
    private String year;
    @NotNull
    private String url;
    @NotNull
    private String poster;
    @NotNull
    @Size(min = 2,max = 50)
    private String genre;
    @NotNull
    private String backdropScreen;

}
