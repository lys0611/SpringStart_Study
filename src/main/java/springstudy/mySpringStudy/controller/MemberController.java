package springstudy.mySpringStudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springstudy.mySpringStudy.domain.Member;
import springstudy.mySpringStudy.service.MemberService;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //주로 조회 시 사용(데이터 조회 시)
    @GetMapping("/members/new")
    public String createForm() {
        return "/members/createMemberForm";
    }

    //주로 데이터를 폼 등에 넣어 전달할 경우 사용(데이터 등록 시)
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

//        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/mermberList";
    }
}
