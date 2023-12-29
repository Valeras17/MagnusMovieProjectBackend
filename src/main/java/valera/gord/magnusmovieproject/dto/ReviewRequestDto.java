package valera.gord.magnusmovieproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ReviewRequestDto {
    @NotNull

    private String rating;
    @NotNull
    @Size(min = 3,max = 1000)
    private String textReview;

}
