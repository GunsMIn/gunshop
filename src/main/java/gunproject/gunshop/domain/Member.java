package gunproject.gunshop.domain;

import gunproject.gunshop.dto.RestApiDto.CreateMemberResponse;
import gunproject.gunshop.dto.RestApiDto.DeleteMemberResponse;
import gunproject.gunshop.dto.RestApiDto.SelectMemberDto;
import gunproject.gunshop.dto.RestApiDto.UpdateMemberResponse;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor
public class Member extends BaseEntity{

    /**
     * 회원은 여러 상품을 주문할 수 있다
     */
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member(){;}

    public Member(String name) {
        this.name = name;
    }

    @Builder
    public Member(Long id, String loginId, String password, String name, Address address) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
    }


    public static CreateMemberResponse of(Member member) {
        return CreateMemberResponse.builder()
                .id(member.id)
                .loginId(member.loginId)
                .password(member.password)
                .name(member.name)
                .address(member.address)
                .build();
    }

    public static SelectMemberDto transSelectDto(Member member) {
        return SelectMemberDto.builder()
                .id(member.id)
                .loginId(member.loginId)
                .password(member.password)
                .name(member.name)
                .address(member.address)
                .build();
    }

    public static UpdateMemberResponse transUpdateDto(Member member) {
        return UpdateMemberResponse.builder()
                .loginId(member.loginId)
                .password(member.password)
                .name(member.name)
                .address(member.address)
                .build();
    }




}
