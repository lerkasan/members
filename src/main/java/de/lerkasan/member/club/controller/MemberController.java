package de.lerkasan.member.club.controller;

import de.lerkasan.member.club.model.Member;
import de.lerkasan.member.club.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("member", new Member());
        return "create-member";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("member") Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "create-member";
        }
        memberService.create(member);
        return "redirect:/members/all";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("members", memberService.getAll());
        return "members-list";
    }
}
