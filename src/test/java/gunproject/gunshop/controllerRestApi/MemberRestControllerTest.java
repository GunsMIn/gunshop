package gunproject.gunshop.controllerRestApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gunproject.gunshop.domain.Address;
import gunproject.gunshop.domain.Member;
import gunproject.gunshop.dto.RestApiDto.CreateMemberRequest;
import gunproject.gunshop.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@WebMvcTest(MemberRestController.class)
class MemberRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MemberService memberService;

    @Test
    @DisplayName("회원가입 성공 테스트")
     void joinSuccess() throws Exception {
        Address address = new Address("서울", "잠실", "2323");
        CreateMemberRequest createMemberRequest = CreateMemberRequest.builder()
                .loginId("gunwoo9595")
                .password("1234")
                .name("김건우")
                .address(address)
                .build();

        when(memberService.join(any())).thenReturn(1190L);

        mockMvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(createMemberRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("중복이름 검사")
    public void check_duplicate() throws Exception {
        Address address = new Address("서울", "잠실", "2323");
        CreateMemberRequest createMemberRequest = CreateMemberRequest.builder()
                .loginId("gunwoo9595")
                .password("1234")
                .name("김건우")
                .address(address)
                .build();
        when(memberService.join(any())).thenThrow(new IllegalStateException("이미 존재하는 회원입니다"));

        mockMvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(createMemberRequest)))
                .andDo(print())
                .andExpect(status().isConflict());

    }

}