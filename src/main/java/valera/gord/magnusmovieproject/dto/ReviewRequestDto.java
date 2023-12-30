package valera.gord.magnusmovieproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class ReviewRequestDto {
    @NotNull
    @Range(min = 1,max = 10)
    private String rating;
    @NotNull
    @Size(max = 1000)
    private String textReview;

}
