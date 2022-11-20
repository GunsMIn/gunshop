package gunproject.gunshop.dto.RestApiDto;

import gunproject.gunshop.domain.Address;
import gunproject.gunshop.domain.Member;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class CreateMemberRequest {

    /*"id": 1,
            "loginId": "lancet",
            "password": "1234",
            "name": "김건우",
            "address": {
        "city": "서울",
                "street": "잠실2동",
                "zipcode": "05502"
    }*/
    private String loginId;

    private String password;

    private String name;

    private Address address;

    public CreateMemberRequest(String loginId, String password, String name, Address address) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public Member toEntity() {
       return Member.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .address(address)
                .build();
    }
}
