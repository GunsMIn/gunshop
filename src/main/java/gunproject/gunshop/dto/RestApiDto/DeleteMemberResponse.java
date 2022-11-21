package gunproject.gunshop.dto.RestApiDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteMemberResponse {

    private Long id;
    private String name;
    private String loginId;

    @Builder
    public DeleteMemberResponse(Long id, String name, String loginId) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
    }
}
