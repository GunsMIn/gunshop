package gunproject.gunshop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원의 이름은 필수입니다.")
    @Size(min=1,max = 10,message="이름은 1~10자 이여야합니다.")
    private String name;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    private String city;

    private String street;

    private String zipcode;
}
