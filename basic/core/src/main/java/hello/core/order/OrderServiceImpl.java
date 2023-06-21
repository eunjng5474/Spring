package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor    // Lombok - final이 붙은 걸로 생성자 만들어줌
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy;  // 인터페이스에만 의존


    @Autowired  // 생성자가 1개면 생략 가능
    // 의존관계 주입 - 생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // 생성자 통해서 할당


//    // 의존관계 주입 - 수정자 주입(setter 주입)
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired  // 생략 불가
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository  = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy  = discountPolicy;
//    }


//    // 의존관계 주입 - 필드 주입 -> 비권장
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;


//    // 의존관계 주입 - 일반 메서드 주입
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    // @Configuration과 싱글톤 - 검증 용도의 코드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
