package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 인터페이스에 대한 구현체가 하나라면 대체로 Impl 추가
@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    // AppConfig 사용
    // => 추상화에만 의존. DIP 만족

    @Autowired  // @Component 사용에 따른 의존관계 자동 주입을 위해 추가
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    // @Configuration과 싱글톤 - 검증 용도의 코드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
