package gunproject.gunshop.dto.RestApiDto;

import gunproject.gunshop.domain.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;


@Getter
@Setter @NoArgsConstructor
public class SelectMemberDto {

    private Long id;

    private String loginId;

    private String password;

    private String name;

    private Address address;

    @Builder
    public SelectMemberDto(Long id, String loginId, String password, String name, Address address) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
    }
}
