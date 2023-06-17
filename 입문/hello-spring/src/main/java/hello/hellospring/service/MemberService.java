package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// 스트링 빈 등록 - 1. 컴포넌트 스캔과 자동 의존관계 설정 -> @Service, @Autowired
//@Service
@Transactional
public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 위 경우 test 시 다른 memberrepository 사용 => 따라서 아래 방식으로 변경
    // 새로 생성하는 것이 아니라 외부에서 넣어줌
    private final MemberRepository memberRepository;

    @Autowired
    // -> 생성자 호출 시 memberRepository가 필요하기 때문에 컨테이너에 넣어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    public static void main(String[] args) {
//        MemberService memberService = new MemberService();
//    }


    // 회원가입
    public Long join(Member member) {

            // 같은 이름이 있는 중복 회원X
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
