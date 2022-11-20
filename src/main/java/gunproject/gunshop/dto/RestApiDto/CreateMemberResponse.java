package gunproject.gunshop.dto.RestApiDto;

import gunproject.gunshop.domain.Address;
import gunproject.gunshop.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class CreateMemberResponse {

    private Long id;

    private String loginId;

    private String password;

    private String name;

    private Address address;

    @Builder
    public CreateMemberResponse(Long id, String loginId, String password, String name, Address address) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .name(name)
                .address(address)
                .build();
    }
}
