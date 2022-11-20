package gunproject.gunshop.dto.RestApiDto;

import gunproject.gunshop.domain.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Access;

import static lombok.AccessLevel.*;

@Getter @NoArgsConstructor(access = PROTECTED) @ToString
public class UpdateMemberRequest {

    private String loginId;

    private String password;

    private String name;

    private Address address;




}
