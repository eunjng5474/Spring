# 1. 객체 지향 설계와 스프링

## 스프링 생태계

- 필수
	- **스프링 프레임워크**
		- 핵심 기술 : 스프링 DI 컨테이너, AOP, 이벤트
		- 웹 기술 : 스프링 MVC, 스프링 WebFlux
		- 데이터 접근 기술 : 트랜잭션, JDBC, ORM 지원, XML 지원
		- 기술 통합 : 캐시, 이메일, 원격접근, 스케줄링
		- 테스트 : 스프링 기반 테스트 지원
		- 언어 : 코틀린, 그루비
	- **스프링 부트**
		- 스프링을 편리하게 사용할 수 있도록 지원, 최근에는 기본으로 사용
		- 단독으로 실행할 수 있는 스프링 애플리케이션을 쉽게 생성
		- Tomcat 같은 웹 서버를 내장해서 별도의 웹 서버를 설치하지 않아도 됨
		- 손쉬운 빌드 구성을 위한 starter 종속성 제공
		- 스프링과 3rd parth(외부) 라이브러리 자동 구성
		- 메트릭, 상태 확인, 외부 구성 같은 프로덕션 준비 기능 제공
		- 관례에 의한 간결한 설정
- 선택
	- 스프링 데이터
	- 스프링 세션
	- 스프링 시큐리티
	- 스프링 Rest Docs
	- 스프링 배치 
	- 스프링 클라우드


## 핵심 개념
- 스프링은 자바 언어 기반의 프레임워크
- 자바 언어의 가장 큰 특징 - **객체 지향 언어**
- 스프링은 객체 지향 언어가 가진 가장 강력한 특징을 살려내는 프레임워크
- 스프링은 좋은 객체 지향 애플리케이션을 개발할 수 있게 도와주는 프레임워크


## 좋은 객체 지향 프로그래밍

### 객체 지향
- 추상화, 캡슐화, 상속, 다형성
- 객체 지향 프로그래밍
	- 컴퓨터 프로그램을 명령어의 목록으로 보는 시각에서 벗어나 여러 개의 독립된 단위, 즉 "객체"들의 모임으로 파악하고자 하는 것이다. 각각의 객체는 메시지를 주고받고, 데이터를 처리할 수 있다. (협력)
	- 프로그램을 유연하고 변경이 용이하게 만들기 때문에 대규모 소프트웨어 개발에 많이 사용된다.

#### 다형성
- **역할과 구현을 분리**
	- 세상이 단순해지고, 유연해지며 변경이 편리해진다.
	- 클라이언트는 대상의 역할(인터페이스)만 알면 된다.
	- 클라이언트는 구현 대상의 내부 구조를 몰라도 되며, 내부 구조가 변경되어도 영향을 받지 않는다.
	- 클라이언트는 구현 대상 자체를 변경해도 영향을 받지 않는다.

	- 자바 언어의 다형성을 활용
		- 역할 = 인터페이스
		- 구현 = 인터페이스를 구현한 클래스, 구현 객체
	- 객체를 설계할 때 역할과 구현을 명확히 분리
	- 객체 설계 시 역할(인터페이스)을 먼저 부여하고, 그 역할을 수행하는 구현 객체 만들기

- 객체의 협력이라는 관계부터 생각
	- 혼자 있는 객체는 없다.
	- 클라이언트: 요청, 서버: 응답
	- 수많은 객체 클라이언트와 객체 서버는 서로 협력 관계를 가진다.

- 자바 언어의 다형성
	- 오버라이딩
	- 다형성으로 인터페이스를 구현한 객체를 실행 시점에 유연하게 변경 가능
	- 클래스 상속 관계도 다형성, 오버라이딩 적용 가능

- 다형성의 본질
	- 인터페이스를 구현한 객체 인스턴스를 실행 시점에 유연하게 변경할 수 있다.
	- 다형성의 본질을 이해하려면 협력이라는 객체사이의 관계에서 시작해야 한다.
	- 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경할 수 있다!


## 좋은 객체 지향 설계의 5가지 원칙(SOLID)

### SRP 단일 책임 원칙
- 한 클래스는 하나의 책임만 가져야 한다.
- 하나의 책임이라는 것은 문맥과 상황에 따라 다르기 때문에 모호하다.
- 중요한 기준은 **변경**이다. 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것
	- ex) UI 변경, 객체의 생성과 사용을 분리

### OCP 개방-폐쇄 원칙 
- 소프트웨어 요소는 **확장**에는 열려 있으나 **변경**에는 닫혀 있어야 한다.
- 다형성 활용. 역할과 구현의 분리를 생각해보자
- 인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능 구현
- 문제점
	- MemberService 클라이언트가 구현 클래스를 직접 선택
	- 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다.
	- 분명 다형성을 사용했지만 OCP 원칙을 지킬 수 없다.
	- 해결하기 위해서는 객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자가 필요하다. 
```java
public class MemberService {
	// private MemberRepository memberRepository = new MemoryMemberRepository();
	private MemberRepository memberRepository = new JdbcMemberRepository();
}
```
	- MemberService는 인터페이스에 의존하지만, 구현 클래스도 동시에 의존
	- MemberService 클라이언트가 구현 클래스를 직접 선택
	- => DIP 위반

### LSP 리스코프 치환 원칙
- 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
- 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것.
  다형성을 지원하기 위한 원칙으로, 인터페이스를 구현한 구현체는 믿고 사용하려면 이 원칙이 필요하다.
- 단순히 컴파일에 성공하는 것을 넘어서는 이야기
	- ex) 자동차 인터페이스의 엑셀은 앞으로 가라는 기능. 뒤로 가게 구현한다면 LSP 위반. 느리더라도 앞으로 가야 한다.

### ISP 인터페이스 분리 원칙
- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
- 자동차 인터페이스 -> 운전 인터페이스, 정비 인터페이스로 분리
- 사용자 클라이언트 -> 운전자 클라이언트, 정비사 클라이언트로 분리
- 분리하면 정비 인터페이스 자체가 변해도 운전자 클라이언트에 영향을 주지 않는다.
- 인터페이스가 명확해지고, 대체 가능성이 높아진다.

### DIP 의존관계 역전 원칙
- 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다."
  의존성 주입은 이 원칙을 따르는 방법 중 하나
- 구현 클래스에 의존하지 말고, 인터페이스에 의존하라는 뜻
- 앞에서 이야기한 **역할(Role)에 의존하게 해야한다**는 것과 같다. 
  객체 세상도 클라이언트가 인터페이스에 의존해야 유연하게 구현체를 변경할 수 있다. 
  구현체에 의존하게 되면 변경이 아주 어려워진다.

### 정리
- 객체 지향의 핵심은 다형성
- 다형성만으로는 쉽게 부품을 갈아 끼우듯이 개발할 수 없고, 구현 객체를 변경할 때 클라이언트 코드도 함께 변경된다.
- 다형성만으로는 OCP, DIP를 지킬 수 없다.


## 객체 지향 설계와 스프링
- 스프링은 다음 기술로 다형성 + OCP, DIP를 가능하게 지원
	- DI(Dependency Injection) : 의존관계, 의존성 주입
	- DI 컨테이너 제공
- 클라이언트 코드의 변경 없이 기능 확장


# 2. 스프링 핵심 원리 이해 - 예제 만들기

## 회원 도메인 설계
- 회원 도메인 요구사항
	- 회원 가입, 조회
	- 회원 등급 : 일반, VIP
	- 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)
![[스크린샷 2023-06-18 오후 9.14.37.png]]

## 회원 도메인 개발
- 회원 엔티티
	- 회원 등급 : main/java/hello.core/member/Grade
	- 회원 엔티티 : member/Member
- 회원 저장소
	- 회원 저장소 인터페이스 : member/MemberRepository
	- 메모리 회원 저장소 구현체 : member/MemoryMemberRepository
		- `HashMap` 동시성 이슈 발생 시 `ConcurrentHashMap` 사용
- 회원 서비스
	- 회원 서비스 인터페이스 : member/MemberService
	- 회원 서비스 구현체 : member/MemberServiceImpl
		- 인터페이스에 대한 구현체가 하나라면 대체로 이름 뒤에 Impl을 추가하여 사용한다.

## 회원 도메인 실행과 테스트
- 회원 도메인 - 회원 가입 main : hello.core/MemberApp
	- 애플리케이션 로직으로 테스트하는 것보다, JUnit 테스트 사용
- 회원 도메인 - 회원 가입 테스트
	- test/java/hello.core/member/MemberServiceTest
- 회원 도메인 설계의 문제점
	- 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존한다. 

## 주문과 할인 도메인 설계
- 주문과 할인 정책
	- 회원은 상품 주문 가능
	- 회원 등급에 따라 할인 정책 적용
	- 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인 적용
	- 할인 정책은 변경 가능성이 높음
- 주문 도메인 협력, 역할, 책임
	1. 주문 생성 : 클라이언트는 주문 서비스에 주문 생성 요청
	2. 회원 조회 : 주문 서비스는 회원 저장소에서 회원 조회
	3. 할인 적용 : 주문 서비스는 회원 등급에 따른 할인 여부를 할인 정책에 위임
	4. 주문 결과 반환 : 주문 서비스는 할인 결과를 포함한 주문 결과 반환
![[스크린샷 2023-06-18 오후 9.28.18.png]]
- 역할과 구현을 분리해서 자유롭게 구현 객체를 조립할 수 있게 설계

## 주문과 할인 도메인 개발
- 할인 정책 인터페이스 : discount/DiscountPolicy
- 정액 할인 정책 구현체 : discount/FixDiscountPolicy
- 주문 엔티티 : order/Order
	- `toString`을 사용하여 해당 형식으로 return 
- 주문 서비스 인터페이스 : order/OrderService
- 주문 서비스 구현체 : order/OrderServiceImpl
	- 주문 생성 요청이 오면, 회원 정보를 조회하고 할인 정책을 적용한 다음 주문 객체를 생성해서 반환
	- 메모리 회원 리포지토리와 고정 금액 할인 정책을 구현체로 생성

## 주문과 할인 도메인 실행과 테스트
- 주문과 할인 정책 실행 : hello.core/OrderApp -> 출력 결과 확인
	- JUnit 테스트 사용
- 주문과 할인 정책 테스트 : test/order/OrderServiceTest


# 3. 스프링 핵심 원리 이해 - 객체 지향 원리 적용

## 새로운 할인 정책 개발
- RateDiscountPolicy 추가 : discount/RateDiscountPolicy
- 테스트 작성 : test/discount/RateDiscountPolicy

## 새로운 할인 정책 적용과 문제점
- 할인 정책을 변경하려면 클라이언트인 orderServiceImpl 코드를 고쳐야 한다.
- 역할과 구현을 분리하였고, 다형성을 활용하고, 인터페이스와 구현 객체를 분리하였다.
- DIP 위반
	- 주문서비스 클라이언트 OrderServiceImpl은 추상(인터페이스) DiscountPolicy 뿐만 아니라, 구체(구현) 클래스 FixDiscountPolicy, RateDiscountPolicy에도 의존하고 있다.
- OCP 위반
	- 기능을 확장해서 변경하면 클라이언트 코드에 영향을 준다.
- 해결 방법 : 인터페이스에만 의존하도록 의존관계 변경
	- order/OrderServiceImpl 수정
		- 단순히 OrderServiceImpl만 수정하면 구현체가 없기 때문에 NPE(Null Pointer Exception) 발생
		- OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야 한다.

## 관심사의 분리
- AppConfig
	- 애플리케이션의 전체 동작 방식을 구성하기 위해, 구현 객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스
	- 애플리케이션의 실제 동작에 필요한 **구현 객체를 생성**
		- MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, FixDiscountPolicy
	- 생성한 객체 인스턴스의 참조(레퍼런스)를 **생성자를 통해서 주입**
		- MemberServiceImpl -> MemoryMemberRepository
		- OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy
![[스크린샷 2023-06-18 오후 9.50.47.png]]
- 객체의 생성과 연결은 AppConfig가 담당
	- appConfig 객체는 MemoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달 -> 의존관계(의존성) 주입
- DIP 완성 : MemberServiceImpl은 MemberRepository인 추상에만 의존
- 관심사의 분리 : 객체를 생성하고 연결하는 역할과 실행하는 역할의 명확한 분리
- OrderServiceImpl - 생성자 주입
	- DiscountPolicy 인터페이스만 의존
	- MemoryMemberRepository, FixDiscountPolicy 객체의 의존관계가 주입된다.
- AppConfig 실행
	- 사용 클래스 - MemberApp, OrderApp
	- 테스트 코드 오류 수정 : MemberServiceTest, OrderServiceTest

## AppConfig 리팩터링
- 현재 AppConfig : 중복 존재, 역할에 따른 구현이 잘 보이지 않음
- AppConfig 리팩터링
	- `new MemoryMemberRepository()` 중복 제거.
	  MemoryMemberRepository를 다른 구현체로 변경할 때 한 부분만 변경하면 된다.
	- 애플리케이션 전체 구성을 빠르게 파악 가능


## 새로운 구조와 할인 정책 적용
![[스크린샷 2023-06-18 오후 10.02.57.png]]
- AppConfig에서 FixDiscountPolicy를 RateDiscountPolicy로 변경해도 구성 영역만 영향을 받고, 사용 영역은 영향을 받지 않는다.
- 즉, 할인 정책을 변경해도 AppConfig만 변경하고, 클라이언트 코드인 OrderServiceImpl을 포함한 사용 영역의 어떤 코드도 변경할 필요가 없다.

## 좋은 객체 지향 설계의 5가지 원칙의 적용
- SRP 단일 책임 원칙
  : 한 클래스는 하나의 책임만 가져야 한다.
	- 관심사를 분리하여 구현 객체를 생성하고 연결하는 책임은 AppConfig가 담당하고, 클라이언트 객체는 실행하는 책임만 담당
- DIP 의존관계 역전 원칙
  : 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다."
	- 클라이언트 코드가 DiscountPolicy 추상화 인터페이스에만 의존하도록 변경
	- AppConfig가 FixDiscountPolicy 객체 인스턴스를 클라이언트 코드 대신 생성하여 클라이언트 코드에 의존관계 주입
- OCP
  : 소프트웨어 요소는 확장에는 열려있으나 변경에는 닫혀있어야 한다.
	- AppConfig가 의존관계를 변경해서 클라이언트 코드에 주입하므로 클라이언트 코드는 변경하지 않아도 된다.
	- 소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀 있다.


## IoC, DI, 그리고 컨테이너

### 제어의 역전 IoC
- 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리
- 프레임워크 vs 라이브러리
	- 프레임워크가 내가 작성한 코드를 제어하고, 대신 실행 -> 프레임워크
	- 내가 작성한 코드가 직접 제어의 흐름을 담당 -> 라이브러리

### 의존관계 주입 DI
- 의존관계는 정적인 클래스 의존 관계와, 실행 시점에 결정되는 동적인 객체(인스턴스) 의존 관계를 분리해서 생각해야 한다.
- 정적인 클래스 의존 관계
	- OrderServiceImpl이 MemoryRepository, DiscountPolicy에 의존한다는 것을 알 수 있지만, 이러한 클래스 의존관계만으로는 실제 어떤 객체가 OrderServiceImpl에 주입될지 알 수 없다.
- 동적인 객체 인스턴스 의존 관계
	- 애플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존 관계
	- 애플리케이션 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결 되는 것을 **의존관계 주입**이라 한다.
	- 객체 인스턴스를 생성하고, 그 참조값을 전달해서 연결된다.
	- 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다.

### IoC 컨테이너, DI 컨테이너
- AppConfig처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것
- 주로 DI 컨테이너라 하며, 어샘블러, 오브젝트 팩토리라고 불리기도 한다.


## 스프링으로 전환하기
- AppConfig 스프링 기반으로 변경
	- `@Configuration`, 각 메서드에 `@Bean` 추가 => 스프링 컨테이너에 스프링 빈으로 등록
- MemberApp, OrderApp에 스프링 컨테이너 적용
- 스프링 컨테이너
	- ApplicationContext
	- 스프링 컨테이너는 `@Configuration`이 붙은 AppConfig를 설정 정보로 사용하고, `@Bean`이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. 이때 스프링 컨테이너에 등록된 객체를 스프링 빈이라 한다.
		- 메서드 이름을 스프링 빈의 이름으로 사용
		- 스프링 빈은 `applicationContext.getBean()` 메서드를 통해 스프링 빈을 찾을 수 있다.



# 4. 스프링 컨테이너와 스프링 빈

## 스프링 컨테이너 생성
```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```
- `ApplicationContext` : 스프링 컨테이너. 인터페이스
- 스프링 컨테이너는 XML을 기반으로 만들 수 있고, 애노테이션 기반의 자바 설정 클래스로 만들 수 있다.
	- `AppConfig` 사용 : 애노테이션 기반

### 스프링 컨테이너 생성 과정
1. 스프링 컨테이너 생성
	- `new AnnotationConfigApplicationContext(AppConfig.class)`
		- 구성 정보 : `AppConfig.class`
2. 스프링 빈 등록
	- 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 사용해서 스프링 빈을 등록
	- 빈 이름은 메서드 이름을 사용하지만, `@Bean(name="memberService2")`와 같이 직접 부여 가능
3. 스프링 빈 의존관계 설정
	- 스프링 컨테이너는 설정 정보를 참고해서 의존관계를 주입


## 컨테이너에 등록된 모든 빈 조회
- test/beanfind/ApplicationContextInfoTest
	- `ac.getBeanDefinitionNames()` : 스프링에 등록된 모든 빈 이름 조회
	- `ac.getBean()` : 빈 이름으로 빈 객체(인스턴스) 조회
	- `getRole()`
		- `ROLE_APPLICATION` : 일반적으로 사용자가 정의한 빈
		- `ROLE_INFRASTRUCTURE` : 스프링이 내부에서 사용하는 빈

## 스프링 빈 조회
### 기본
- 스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적 조회 방법 : ApplicationContextBasicFindTest
	- `ac.getBean(빈이름, 타입)`, `ac.getBean(타입)` 
	- 조회 대상 스프링 빈이 없으면 예외 발생
		- `NoSuchBeanDefinitionException: No bean named 'xxxxx' available`
		- 예외 테스트 필요

### 동일한 타입이 둘 이상
- ApplicationContextSameBeanFindTest
- 타입으로 조회 시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생하므로, 빈 이름 지정
- `ac.getBeanOfType()` 을 통해 해당 타입의 모든 빈 조회

### 상속 관계
- 부모 타입으로 조회하면 자식 타입도 함께 조회
- 모든 자바 객체의 최고 부모인 Object 타입으로 조회 시, 모든 스프링 빈 조회
- test/ApplicationContextExtendsFindTest


## BeanFactory와 ApplicationContext

### BeanFactory
- 스프링 컨테이너의 최상위 인터페스로, 스프링 빈을 관리하고 조회하는 역할
- `getBean()` 제공

### ApplicationContext
- BeanFactory 기능을 모두 상속받아서 제공
- ApplicationContext가 제공하는 부가기능
	- 메시지소스를 활용한 국제화 기능 : ex) 한국에서 들어오면 한국어, 영어권에서 들어오면 영어로 출력
	- 환경변수 : 로컬, 개발, 운영 등을 구분해서 처리
	- 애플리케이션 이벤트 : 이벤트를 발행하고 구독하는 모델을 편리하게 지원
	- 편리한 리소스 조회 : 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회


## 다양한 설정 형식 지원 - 자바 코드, XML
- 애노에티션 기반 자바 코드 설정 사용 
	- `AnnotationConfigApplicationContext` 클래스를 사용하면서 자바 코드로 된 설정 정보 넘기기
- XML 설정 사용
	- `GenericXmlApplicationContext`를 사용하면서 xml 설정 파일 넘기기
	- test/xml/XmlAppContext
	- main/resources/appConfig.xml


## 스프링 빈 설정 메타 정보 - BeanDefinition
- BeanDefinition : 빈 설정 메타정보
	- BeanDefinition을 직접 생성해서 스프링 컨테이너에 등록할 수도 있다.
	- test/beandefinition/BeanDefinitionTest
	- `@Bean`, `<bean>` 당 하나씩 메타 정보가 생성되고, 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈 생성
	- 기본값은 Scope(싱글톤)
![[Screenshot 2023-06-19 at 3.38.23 PM.png]]



# 5. 싱글톤 컨테이너

## 웹 애플리케이션과 싱글톤
- 스프링 없는 순수한 DI 컨테이너 테스트 : test/singleton/SingletonTest
	- 스프링 없는 순수한 DI 컨테이너인 AppConfig는 요청을 할 때마다 객체를 새로 생성
	- -> 메모리 낭비가 심하기 때문에, 해당 객체가 1개만 생성되고 공유하도록 싱글톤 패턴으로 설계

## 싱글톤 패턴
- 클래스의 인스턴스가 1개만 생성되는 것을 보장하는 디자인 패턴
- private 생성자를 통해 외부에서 임의로 new 키워드를 사용하여 객체 생성하는 것을 막아야 한다.
- 싱글톤 패턴을 적용한 예제 코드 : test/singleton/SingletonService
	- static 영역에 객체 instance를 미리 하나 생성해서 올려둔다
	- 이 객체 인스턴스가 필요하면 `getInstance()` 메서드를 통해서만 조회 가능. 항상 같은 인스턴스 반환
	- 생성자를 private으로 막아 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
- 싱글톤 패턴을 사용하는 테스트 코드 : test/singleton/SingletonServiceTest
	- private으로 생성자를 막아두었기 때문에 new 불가 -> 컴파일 에러 
	- 호출할 때마다 같은 객체 인스턴스를 반환한다. 
- 싱글톤 패턴 문제점
	- 패턴을 구현하는 코드 자체가 많이 들어간다.
	- 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP 위반
	- 클라이언트가 구체 클래스에 의존해 OCP 원칙을 위반할 가능성이 높다.
	- 테스트 하기 어려우며, 내부 속성 변경 및 초기화가 어렵다.
	- private 생성자로 자식 클래스를 만들기 어렵다.
	- 유연성이 떨어진다.

## 싱글톤 컨테이너
- 스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도 객체 인스턴스를 싱글톤으로 관리
	- 컨테이너 객체는 하나만 생성해서 관리
- 스프링 컨테이너는 싱글톤 컨테이너 역할을 하고, 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.
- 싱글톤 패턴의 단점을 해결하면서 객체를 싱글톤으로 유지 가능
- 스프링 컨테이너를 사용하는 테스트 코드 : test/singleton/singletonTest
	- 스프링 컨테이너 덕분에 고객의 요청이 올때마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유하여 효율적으로 재사용 가능

## 싱글톤 방식의 주의점
- 싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지하게 설계하면 안된다.
- **무상태**로 설계
	- 특정 클라이언트에 의존적인 필드가 있으면 안된다.
	- 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
	- 가급적 읽기만 가능해야 한다.
	- 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
- 스프링 빈의 필드에 공유 값을 설정하지 않도록 주의
- 상태를 유지할 경우 발생하는 문제점 예시 : test/singleton/StatefulService, StatefulServiceTest
	- statefulService의 `price` 필드는 공유되는 필드인데 특정 클라이언트가 값을 변경한다.
	- 사용자 A의 주문금액은 10000원이 되어야 하는데 20000원이라는 결과가 나옴


## @Configuration과 싱글톤
- AppConfig,  test/singleton/ConfigurationSingletonTest
	- 스프링 컨테이너가 각각 `@Bean`을 호출해서 스프링 빈을 생성하기 때문에, `memberRepository()`가 3번 호출되어야 할 것 같다.
		- 1. 스프링 컨테이너가 스프링 빈에 등록하기 위해 @Bean이 붙어있는 memberRepository() 호출
		- 2.memberService() 로직에서 memberRepository() 호출
		- 3. orderService() 로직에서 memberRepository() 호출
	- 예상 호출 결과 - 순서는 보장 X
		- call AppConfig.memberService  
		- call AppConfig.memberRepository  
		- call AppConfig.memberRepository  
		- call AppConfig.orderService  
		- call AppConfig.memberRepository  
	  - 실제 호출 결과 
		  - call AppConfig.memberService  
		  - call AppConfig.memberRepository  
		  - call AppConfig.orderService  
		  - => @Configuration 덕분

## @Configuration과 바이트코드 조작의 마법
- 스프링 컨테이너는 싱글톤 레지스트리이기 때문에 스프링 빈이 싱글톤이 되도록 보장해주어야 한다. 따라서 스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용한다.
- ConfigurationSingletonTest - configurationDeep
	- `AnnotationConfigApplicationContext`에 파라미터로 넘긴 값은 스프링 빈으로 등록되기 때문에, AppConfig도 스프링 빈이 된다.
	- AppConfig 스프링 빈을 조회해서 클래스 정보를 출력해보면, 클래스 명 뒤에 xxxCGLIB가 붙어있다.
		- 내가 만든 클래스가 아니라 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 클래스를 스프링 빈으로 등록한 것
		- 이 임의의 다른 클래스가 싱글톤이 보장되도록 해준다.
			- `@Bean`이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
			- AppConfig@CGLIB는 AppConfig의 자식 타입이므로 AppConfig 타입으로 조회 가능
- @Configuration을 적용하지 않고 @Bean만 적용하면?
	- 순수한 AppConfig로 스프링 빈에 등록
	- MemberRepository가 총 3번 호출되고, 각각 다른 MemoryMemberRepository 인스턴스를 가진다.
	- 즉, @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.



# 6. 컴포넌트 스캔

## 컴포넌트 스캔과 의존관계 자동 주입 시작하기
- AutoAppConfig - `@Configuration`, `@ComponentScan` 추가
	- 컴포넌트 스캔을 사용하면 `@Component` 애노테이션이 붙은 클래스를 스프링 빈으로 등록한다.
	- 이 때 `@Configuration`이 붙은 설정 정보도 자동으로 등록되기 때문에, AppConfig 등을 스캔 대상에서 제외하기 위해 `excludeFilters` 사용
- MemoryMemberRepository, RateDiscountPolicy에 `@Component` 추가 
- MemberServiceImpl, OrderServiceImpl에 `@Component`, `@Autowired` 추가
	- `@Autowired`를 통해 의존관계 자동 주입 (스프링 컨테이너가 관리하는 스프링 빈일 때)
		- `getBean(MemberRepository.class)`와 동일한 방식
	- 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용하며, `@Component("memberService2")`와 같이 이름 직접 지정 가능
- test/AutoAppConfigTest
	- `AnnotationConfigApplicationContext` 를 사용하는 것은 동일
	- 설정 정보로 `AutoAppConfig` 클래스를 넘겨준다
	

## 탐색 위치와 기본 스캔 대상
- `@ComponentScan`에 `basePackages`나 `basePackageClasses`를 추가하여 탐색할 패키지의 시작 위치를 지정할 수 있다.
	- basePackages : 탐색할 패키지의 시작 위치
		- `basePackages = {"hello.core", "hello.service"}`와 같이 여러 개 지정 가능
	- basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위치로 지정
	- 미지정 : `@ComponentScan`이 붙은 설정 정보 클래스의 패키지가 시작 위치 
		- 프로젝트 시작 루트에 AppConfig와 같은 메인 설정 정보를 두고 `@ComponentScan` 애노테이션을 붙여 basePackages 지정 생략하는 것을 권장 
		- 스프링 부트 사용 시 `@SpringBootApplication`을 프로젝트 시작 루트 위치에 두는 것이 관례
- 컴포넌트 스캔은 `@Component`뿐만 아니라 다음 내용도 대상에 포함 (해당 클래스 소스 코드에 `@Component`를 포함하기 때문)
	- @Component : 컴포넌트 스캔에서 사용
	- @Controller : 스프링 MVC 컨트롤러에서 사용
	- @Service : 스프링 비즈니스 로직에서 사용 
	- @Repository : 스프링 데이터 접근 계층에서 사용 
	- @Configuration : 스프링 설정 정보에서 사용

## 필터
- `includeFilters` : 컴포넌트 스캔 대상을 추가로 지정
- `excludeFilters` : 컴포넌스 스캔에서 제외할 대상 지정
- test/scan/filter/MyIncludeComponent, MyExcludeComponent, BeanA, BeanB
- test/scan/filter/ComponentFilterAppConfigTest
- FilterType 옵션
	- ANNOTATION : 기본값. 애노테이션 인식해서 동작. 생략 가능
	- ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작
	- ASPECTJ : AspectJ 패턴 사용
	- REGEX : 정규 표현식
	- CUSTOM : `TypeFilter`라는 인터페이스를 구현해서 처리

## 중복 등록과 충돌
- 자동 빈 등록끼리 이름이 같은 경우 오류 발생 : `ConflictingBeanDefinitionException` 예외 발생
- 수동 빈 등록과 자동 빈 등록에서 빈 이름이 충돌하면 수동 빈 등록이 우선권 가짐(수동 빈이 자동 빈을 오버라이딩)
	- 스프링 부트는 오류가  발생하도록 수정함




# 7. 의존관계 자동 주입

## 다양한 의존관계 주입 방법
- main/order/OrderServiceImpl

### 생성자 주입
- 생성자 호출시점에 딱 1번만 호출되는 것이 보장
- **불변, 필수** 의존관계에 사용
- 생성자가 1개만 있으면 `@Autowired` 생략 가능

### 수정자 주입(setter 주입)
- setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해 의존관계를 주입
- **선택, 변경** 가능성이 있는 의존관계에 사용
- 자바빈 프로퍼티 규약(`setXxx, getXxx` 메서드 사용)의 수정자 메서드 방식을 사용하는 방법
- `@Autowired`의 기본 동작은 주입할 대상이 없으면 오류 발생
	- 주입할 대상이 없어도 동작하게 하려면 `@Autowired(required = false)`

### 필드 주입
- 외부에서 불가능해서 테스트 하기 힘들며, DI 프레임워크가 없으면 아무것도 할 수 없다.
- 애플리케이션의 실제 코드와 관계 없는 테스트 코드나, 스프링 설정을 목적으로 하는 @Configuration 같은 곳 외에는 사용X

### 일반 메서드 주입
- 한 번에 여러 필드를 주입 받을 수 있지만, 일반적으로 잘 사용하지 않음


## 옵션 처리
- 주입할 스프링 빈이 없어도 동작해야 할 때가 있지만, `@Autowired`만 사용하면 required 옵션의 기본값이 true로 되어 있어 자동 주입 대상이 없으면 오류 발생
- 자동 주입 대상을 옵션으로 처리하는 방법
	- test/autowired/AutowiredTest
	- `@Autowired(required=false)` : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안 됨
	- `org.springframework.lang.@Nullable` : 자동 주입할 대상이 없으면 null이 입력된다
	- `Optional<>` : 자동 주입할 대상이 없으면 `Optional.empty`가 입력


## 생성자 주입 권장
- 불변
	- 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다. 오히려 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다.
	- 수정자 주입을 사용하면, `setXxx` 메서드를 public으로 열어두어야 하는데, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다.
		- 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다.
- 누락
	- test/order/OrderServiceImplTest
	- 수정자 의존관계일 경우 실행은 되지만 Null Point Exception 발생
		- memberRepository, discountPolicy 모두 의존관계 주입이 누락되었기 때문
	- 생성자 주입 사용 시 주입 데이터를 누락하면 컴파일 오류가 발생해 주입해야 하는 값을 바로 알 수 있다.
- final 키워드
	- 생성자 주입을 사용하면 필드에 final 키워드를 사용할 수 있어서, 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.
	- 생성자 주입 방식만 final 키워드 사용 가능
- 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우 수정자 주입 방식을 옵션으로 부여


## 롬복과 최신 트렌드
- build.gradle에 라이브러리 및 환경 추가 
- Settings > Annotation Processors > Enable annotation processing 체크
- `@Getter, @Setter` 테스트 - HelloLombok

- main/order/OrderServiceImpl
- 롬복 라이브러리가 제공하는 `@RequiredArgsConstructor` 기능을 사용하면 final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.
- 최근에는 생성자를 1개만 두고 `@Autowired`를 생략하며, Lombok 라이브러리의 `@RequiredArgsConstructor`를 함께 사용


## 조회 빈이 2개 이상 - 문제
- `@Autowired`는 타입으로 조회하기 때문에 `ac.getBean(DiscountPolicy,class)`와 유사하게 동작
- 선택된 빈이 2개 이상일 때 문제 발생
	- FixDiscountPolicy, RateDiscountPolicy 둘 다 스프링 빈으로 선언할 경우, 의존관계 자동 주입 실행시 NoUniqueBeanDefinitionException 오류 발생

## 조회 대상 빈이 2개 이상일 때 해결 방법
- OrderServiceImpl, discount/RateDiscountPolicy, FixDiscountPolicy

### @Autowired 필드명
- `@Autowired`는 **먼저 타입 매칭을 시도**하고, 여러 빈이 있으면 필드 명, 파라미터 명으로 빈 이름 매칭
	- OrderServiceImpl에서 생성자 주입 부분에서 `discountPolicy`를 `rateDiscountPolicy`로 수정하면 AutoAppConfig 테스트 성공
```java
@Autowired
public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy **rateDiscountPolicy**) {  
this.memberRepository = memberRepository;  
this.discountPolicy = **rateDiscountPolicy**;  
}
```

### @Qualifier
- `@Qualifier`는 추가 구분자를 붙여주는 방법. 주입시 추가적인 방법을 제공하는 것으로, 빈 이름을 변경하는 것은 아니다.
- 빈 등록시 `@Qualifier`를 붙이고, 이름 등록
- OrderServiceImpl에서 주입 시 `@Qualifier`를 붙여주고 등록한 이름을 적어준다
- `@Qualifier`끼리 매칭 -> 빈 이름 매칭 -> NoSuchBeanDefinitionException 예외 발생

### @Primary
- `@Primary`는 우선순위를 정하는 방법. `@Autowired` 시에 여러 빈이 매칭되면 `@Primary`가 우선권을 가짐
- 메인 데이터베이스의 커넥션을 획득하는 스프링 빈은 `@Primary`를 적용해서 편리하게 조회하고, 서브 데이터베이스 커넥션 빈을 획득할 때는 `@Qualifier`를 지정해서 명시적으로 획득하는 방식으로 사용하면 코드를 깔끔하게 유지할 수 있다.
- `@Primary`는 기본값처럼 동작하고, `@Qualifier`는 매우 상세하게 동작하기 때문에 `@Qualifier`가 우선권이 높다. 


## 애노테이션 직접 만들기
- `@Qualifier("mainDiscountPolicy")`와 같이 문자를 적으면 컴파일시 타입 체크가 안된다.
- annotation/MainDiscountPolicy
- discount/RateDiscountPolicy, OrderServiceImpl에 `@MainDiscountPolicy` 추가


## 조회한 빈이 모두 필요할 때, List, Map
- test/autowired/AllBeanTest
- 로직 분석
	- DiscountService는 Map으로 만든 모든 DiscountPolicy를 주입받는다. 이때, fixDiscountPolicy, rateDiscountPolicy가 주입된다.
	- `discount()`메서드는 discountCode로 넘어오는 스프링 빈을 map에서 찾아서 실행
- 주입 분석
	- `Map<String, DiscountPolicy>` : map의 키에 스프링 빈의 이름을 넣고, 그 값으로 DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담는다.
	- `List<DiscountPolicy>` : DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담는다.
	- 만약 해당하는 타입의 스프링 빈이 없다면 빈 컬렉션이나 Map을 주입한다.
- 스프링 컨테이너를 생성하면서 스프링 빈 등록하기
	- `new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);`
		- `new AnnotationConfigApplicationContext()`를 통해 스프링 컨테이너 생성
		- `AutoAppConfig.class, DiscountService.class`를 파라미터로 넘기면서 해당 클래스를 자동으로 스프링 빈으로 등록


## 자동, 수동의 올바른 실무 운영 기준
- 업무 로직 빈
	- 웹을 지원하는 컨트롤러, 핵심 비즈니스 로직이 있는 서비스, 데이터 계층의 로직을 처리하는 리포지토리 등이 모두 업무 로직. 보통 비즈니스 요구사항을 개발할 때 추가되거나 변경
- 기술 지원 빈
	- 기술적인 문제나 공통 관심사(AOP)를 처리할 때 주로 사용. 데이터베이스 연결이나, 공통 로그 처리처럼 업무 로직을 지원하기 위한 하부 기술이나 공통 기술
- 업무 로직은 자동 기능을 적극 사용하는 것이 좋고, 애플리케이션에 광범위하게 영향을 미치는 기술 지원 객체는 수동 빈으로 등록하는 것이 유지보수에 좋다.
- 앞서 DiscountService가 의존관계 자동 주입으로 `Map<String, DiscountPolicy>`에 주입을 받는 경우,어떤 빈들이 주입되는지, 각 빈들의 이름은 무엇인지 쉽게 파악하기 힘들다. 이 경우 수동 빈으로 등록하거나, 자동으로 등록 시 특정 패키지에 같이 묶어두는 것이 좋다.




# 8. 빈 생명주기 콜백

## 빈 생명주기 콜백 시작
- 데이터베이스 커넥션 풀이나, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고, 애플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하려면 객체의 초기화와 종료 작업이 필요하다.
- test/lifecycle/NetworkClient, BeanLifeCycleTest
- NetworkClient는 애플리케이션 시작 시점에 `connect()`를 호출해서 연결을 맺어두어야 하고, 애플리케이션이 종료되면 `disConnect()`를 호출해서 연결을 끊어야 한다.
	- 객체를 생성하는 단계에는 url이 없고, 객체를 생성한 다음에 외부에서 수정자 주입을 통해 `setUrl()`이 호출되어야 url이 존재하게 된다.

- 스프링 빈은 **객체를 생성**하고, **의존관계 주입**이 다 끝난 다음에야 데이터를 사용할 준비가 완료된다. 따라서 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 한다.
- 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해 초기화 시점을 알려주는 다양한 기능을 제공한다. 또한 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다.
- **스프링 빈의 이벤트 라이프사이클**
	- 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
	- **초기화 콜백** : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
	- **소멸전 콜백** : 빈이 소멸되기 직전에 호출

- 생성자는 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다. 반면 초기화는 이렇게 생성된 값들을 활용해서 외부 커넥션을 연결하는 등 무거운 동작을 수행한다. 
  따라서 객체를 생성하는 부분과 초기화하는 부분을 명확하게 나누는 것이 유지보수 관점에서 좋다. 
  초기화 작업이 내부 값들만 약간 변경하는 정도로 단순하다면 생성자에서 한번에 다 처리하는게 나을 수 있다.
- 싱글톤 빈들은 스프링 컨테이너가 종료될 때 싱글톤 빈들도 함께 종료되기 때문에 스프링 컨테이너가 종료되기 직전에 소멸전 콜백이 일어난다.

## 스프링의 빈 생명주기 콜백 지원 방법

### 인터페이스 InitializingBean, DisposableBean
- `implements InitializingBean, DisposableBean`
	- InitializingBean은 `afterPropertiesSet()` 메서드로 초기화 지원
	- DisposableBean은 `destroy()` 메서드로 소멸 지원 
- 스프링 전용 인터페이스로, 해당 코드가 스프링 전용 인터페이스에 의존한다.
- 초기화, 소멸 메서드의 이름 변경 불가, 코드를 고칠 수 없는 외부 라이브러리에 적용 불가

### 빈 등록 초기화, 소멸 메서드 지정
- `@Bean(initMethod = "init", destroyMethod = "close")`
- 메서드 이름을 자유롭게 줄 수 있고, 스프링 빈이 스프링 코드에 의존하지 않는다.
- 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.
- 종료 메서드 추론
	- 라이브러리는 대부분 close, shutdown이라는 이름의 종료 메서드 사용
	- @Bean의 `destroyMethod`는 기본값이 `(inferred)`(추론)으로 등록되어 있어서, close, shutdown이라는 이름의 메서드를 자동으로 호출해준다.
	- 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작한다.

### 애노테이션 @PostConstruct, @PreDestroy
- 최신 스프링에서 가장 권장하는 방법으로, 애노테이션만 붙이면 되므로 매우 편리하다.
- 패키지를 보면 `javax.annotation.PostConstruct`로, 자바 표준이기 때문에 스프링이 아닌 다른 컨테이너에서도 동작한다.
- 외부 라이브러리에는 적용하지 못하기 때문에 필요하다면 @Bean의 기능을 사용




# 9. 빈 스코프

## 빈 스코프란
- 스코프 : 빈이 존재할 수 있는 범위
- 스프링이 지원하는 스코프
	- **싱글톤** : 기본 스코프. 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프
	- **프로토타입** : 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 매우 짧은 범위의 스코프
	- **웹 관련 스코프**

## 프로토타입 스코프
- 싱글톤 스코프의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 스프링 빈을 반환한다.
	- 1. 싱글톤 스코프의 빈을 스프링 컨테이너에 요청
	- 2. 스프링 컨테이너는 본인이 관리하는 스프링 빈을 반환
	- 3. 이후에 스프링 컨테이너에 같은 요청이 와도 같은 객체 인스턴스의 스프링 빈을 반환
- 프로토타입 스코프를 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성해서 반환한다.
	- 1. 프로토타입 스코프의 빈을 스프링 컨테이너에 요청
	- 2. 스프링 컨테이너는 이 시점에 프로토타입 빈을 생성하고, 필요한 의존관계를 주입
	- 3. 스프링 컨테이너는 생성한 프로토타입 빈을 클라이언트에 반환
	- 4. 이후에 스프링 컨테이너에 같은 요청이 오면 항상 새로운 프로토타입 빈을 생성해서 반환
- 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리한다.
  클라이언트에 빈을 반환하고, 이후 스프링 컨테이너는 생성된 프로토타입 빈을 관리하지 않는다.
  프로토타입 빈을 관리할 책임을 클라이언트에 있으므로 `@PreDestroy`같은 종료 메서드가 호출되지 않는다.
- 싱글톤 스코프 빈 테스트 : test/scope/SingletonTest - `singletonBeanTest()`
	- 빈 초기화 메서드를 실행하고, 같은 인스턴스의 빈을 조회하고, 종료 메서드까지 정상 호출된다.
- 프로토타입 스코프 빈 테스트 : test/scope/PrototypeTest - `prototypeBeanTest()`
	- 싱글톤 빈은 스프링 컨테이너 생성 시점에 초기화 메서드가 실행되지만, 프로토타입 스코프의 빈은 스프링 컨테이너에서 빈을 조회할 때 생성되고, 초기화 메서드도 실행된다.
	- 프로토타입 빈을 2번 조회했으므로 다른 스프링 빈이 생성되고, 초기화도 2번 시행되었다.
- 프로토타입 빈의 특징
	- 스프링 컨테이너에 요청할 때마다 새로 생성된다
	- 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입, 초기화까지만 관여한다
	- 종료 메서드가 호출되지 않는다
	- 프로토타입 빈은 이를 조회한 클라이언트가 관리해야 한다. 종료 메서드 호출도 클라이언트가 해야한다.

### 싱글톤 빈과 함께 사용시 문제점
- test/scope/SingletonWithPrototypeTest1
- 스프링 컨테이너에 프로토타입 빈 직접 요청
	- 1. 클라이언트A는 스프링 컨테이너에 프로토타입 빈을 요청
	- 2. 스프링 컨테이너는 프로토타입 빈을 새로 생성해서 반환(x01). 해당 빈의 count 필드 값은 0
	- 3. 클라이언트는 조회한 프로토타입 빈에 `addCount()`를 호출하면서 count 필드를 +1
	- 결과적으로 프로토타입 빈(x01)의 count는 1
	- 클라이언트B가 요청하면 똑같은 과정을 거쳐, 프로토타입 빈(x02)의 count는 1이 된다.
- 싱글톤에서 프로토타입 빈 사용1
	- `clientBean`은 싱글톤이므로, 보통 스프링 컨테이너 생성 시점에 함께 생성되고 의존관계 주입도 발생
	- 1. `clientBean`은 의존관계 자동 주입을 사용. 주입 시점에 스프링 컨테이너에 프로토타입 빈을 요청
	- 2. 스프링 컨테이너는 프로토타입 빈을 생성해서 `clientBean`에 반환. 프로토타입 빈의 count 필드 값은 0
	- `clientBean`은 프로토타입 빈을 내부 필드에 보관한다.
- 싱글톤에서 프로토타입 빈 사용2
	- 클라이언트A는  `clientBean`을 스프링 컨테이너에 요청해서 받는다. 싱글톤이므로 항상 같은 `clientBean`이 반환된다.
	- 3. 클라이언트A는 `clientBean.logic()`을 호출 
	- 4. `clientBean`은 prototypeBean의 `addCount()`를 호출해서 프로토타입 빈의 count를 증가해 count값이 1이 된다.
- 싱글톤에서 프로토타입 빈 사용3
	- 클라이언트B는 `clientBean`을 스프링 컨테이너에 요청해서 받는다. 싱글톤이므로 항상 같은 `clientBean`이 반환된다.
	- `clientBean`이 내부에 가지고 있는 프로토타입 빈은 이미 과거에 주입이 끝난 빈이다. 주입 시점에 스프링 컨테이너에 요청해서 프로토타입 빈이 새로 생성된 것이지, 사용할 때마다 새로 생성되는 것은 아니다.
	- 5. 클라이언트 B는 `clientBean.logic()`을 호출한다.
	- 6. `clientBean`은 prototypeBean의 `addCount()`를 호출해서 프로토타입 빈의 count를 증가한다. 원래 count 값이 1이었으므로 2가 된다.

- 스프링은 일반적으로 싱글톤 빈을 사용하므로, 싱글톤 빈이 프로토타입 빈을 사용하게 된다. 그런데 싱글톤 빈은 생성 시점에만 의존관계 주입을 받기 때문에, 프로토타입 빈이 새로 생성되기는 하지만 싱글톤 빈과 함께 계속 유지되는 것이 문제다.

### 싱글톤 빈과 함께 사용시 Provider로 문제 해결
- Dependency Lookup(DL, 의존관계 조회(탐색)) : 의존관계를 외부에서 주입(DI) 받는 게 아니라 직접 필요한 의존관계를 찾는 것

#### ObjectFactory, ObjectProvider
- 지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스를 제공하는 것이 `ObjectProvider`이다. 
	- `prototypeBeanProvider.getObject()`을 통해서 항상 새로운 프로토타입 빈이 생성된다.
	- `ObjectProvider`의 `getObject()`를 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다.(DL)
	- 스프링이 제공하는 기능을 사용하지만, 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기 쉬워진다.
- ObjectFactory : 단순한 기능. 별도의 라이브러리 필요 없음. 스프링에 의존
- ObjectProvider : ObjectFactory 상속. 옵션, 스트림 처리 등 편의 기능이 많고 별도의 라이브러리 필요 없음. 스프링에 의존

#### JSR-330 Provider
- javax.inject.Provider라는 JSR-330 자바 표준 사용
- gradle에 `javax.inject:javax.inject:1` 라이브러리 추가 
- `provider.get()`을 통해 항상 새로운 프로토타입 빈이 생성된다. 
- `provider`의 `get()`을 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환(DL)
- `get()`메서드 하나로 기능이 매우 단순. 별도의 라이브러리 필요. 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용 가능 


## 웹 스코프
- 웹 스코프는 웹 환경에서만 동작하며, 프로토타입과 달리 스프링이 해당 스코프의 종료시점까지 관리하기 때문에 종료 메서드가 호출된다. 
- 종류
	- **request** : HTTP 요청 하나가 들어오고 나갈 때까지 유지되는 스코프. 각각의 HTTP 요청마다 별도의 빈 인스턴스가 생성되고 관리된다.
	- **session** : 웹 세션이 생성되고 종료될 때까지 유지되는 스코프
	- **application** : 웹의 서블릿 컨텍스트와 동일한 생명주기를 가지는 스코프 
	- **websocket** : 웹 소켓과 동일한 생명주기를 가지는 스코프 

### request 스코프 예제 만들기
- build.gradle에 web 라이브러리 추가
- hello.core.CoreApplication의 main 메서드를 실행하면 웹 애플리케이션이 실행된다. 
- 스프링부트는 웹 라이브러리가 없으면 `AnnotationConfigApplicationContext`을 기반으로 구동하며, 
  웹 라이브러리가 추가되면 `AnnotationConfigServletWebServerApplicationContext`를 기반으로 애플리케이션을 구동한다.
- 8080 포트를 다른 곳에서 사용중이어서 오류 발생할 경우 main/resources/application.properties에서 `server.port=9090` 추가 

- request 스코프 예제 - main/common/MyLogger
	- 기대하는 공통 포멧: `[UUID][requestURL]{message}`
	- `@Scope(value = "request")`를 사용해서 request 스코프로 지정

## 스코프와 Provider

## 스코프와 프록시

