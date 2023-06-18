package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

//    private MemberService memberService;
//
//    // setter 주입 -> 아무나 열 수 있게 열려있다는 단점
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

//    // 필드 주입 -> 비권장
//    @Autowired private MemberService memberService;


    // 생성자 주입
    private final MemberService memberService;

    @Autowired
    // 스트링 빈 등록 - 1. 컴포넌트 스캔과 자동 의존관계 설정
    // MemberService에서 @Service, @Autowired를,
    // MemoryMemberRepository에서 @Repository 추가해줘야 함
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
