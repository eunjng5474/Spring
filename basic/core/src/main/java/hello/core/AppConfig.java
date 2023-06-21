package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션 환경 구성에 대한 건 AppConfig에서 다 한다.
@Configuration  // Spring으로 변경 -> @Configuration, 각 메서드에 @Bean 추가 => 스프링 컨테이너에 추가됨
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    // 예상 호출 결과 - 순서는 보장 X
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 실제 호출 결과 - ConfigurationSingletonTest
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // => @Configuration 덕분

    // 만약 @Configuration을 지운다면 싱글톤 적용X - 예상 호출 결과대로 됨
        // @Autowired MemberRepository memberRepository;로 해결은 가능함

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();    // 정액 할인 -> 정률 할인 변경 시 이 부분만 수정하면 된다.
    }
}
