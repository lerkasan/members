package de.lerkasan.member.club.controller;

import de.lerkasan.member.club.model.Member;
import de.lerkasan.member.club.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    @Test
    public void getHomeTest() throws Exception {
        List<Member> expected = Arrays.asList(new Member(), new Member(), new Member());
        when(memberService.getAll()).thenReturn(expected);
        mvc.perform(get("/")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("members"))
                .andExpect(model().attribute("members", expected))
                .andExpect(view().name("members-list"));
        verify(memberService, times(1)).getAll();
    }
}
