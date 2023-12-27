package valera.gord.magnusmovieproject.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageDto {
    private List<UserResponseDto> result;
    private int totalAmountPages;
    private boolean isFirst;
    private boolean isLast;
    private int pageNo;
    private long totalAmountUsers;
    private int pageSize;
}
