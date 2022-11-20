package gunproject.gunshop.dto.RestApiDto;

import gunproject.gunshop.domain.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter @NoArgsConstructor(access = PROTECTED)
public class UpdateMemberResponse {

    private String loginId;

    private String password;

    private String name;

    private Address address;

    @Builder
    public UpdateMemberResponse(String loginId, String password, String name, Address address) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
    }
}
