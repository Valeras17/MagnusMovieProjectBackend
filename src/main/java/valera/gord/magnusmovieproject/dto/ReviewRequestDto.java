package valera.gord.magnusmovieproject.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @Min(1)
    @Max(5)
    private Integer rating;
    @NotNull
    @Size(max = 1000)
    private String textReview;

}
