package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", // 탐색할 패키지의 시작 위치
        basePackageClasses = AutoAppConfig.class,   // 지정한 클래스의 패키지를 탐색 시작 위치로 지정
        // 지정하지 않으면 이 클래스의 패키지가 시작 위치가 됨
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // @Configuration이 붙은 설정 정보는 자동 등록되지 않도록 제외(ex.AppConfig)
)
public class AutoAppConfig {

//    // 수동 빈 등록이 우선
//    @Bean(name = "memoryMemberRepository")  // 중복 빈 등록 테스트
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
