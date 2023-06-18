# 프로젝트 환경설정

## 프로젝트 생성 
https://start.spring.io/
- Project : Gradle - Groovy
- Language : Java
- Spring Boot : 2.7.12
	- 3.0 이상 선택 시 Java 17 이상 사용, javax 패키지 이름을 jakarta로 변경, H2 데이터베이스 2.1.214 버전 이상 사용 필요
- Project Metadata
	- Group : hello
	- Artifact : hello-spring
	- Packaging : Jar
	- Java : 11
- Dependencies : Spring Web, Thymeleaf

- 설정 변경
	- Settings - Build, Execution, Deployment - Gradle
		- Build and run using, Run test usings : Gradle -> IntelliJ IDEA
		- Gradle JVM : java 11
![[스크린샷 2023-06-18 오후 5.11.23.png]]

## 라이브러리 확인
- 스프링 부트 라이브러리 
	- spring-boot-starter-web
		- spring-boot-starter-tomcat : 톰캣(웹서버)
		- spring-webmvc : 스프링 웹 MVC
	- spring-boot-starter-thymeleaf : 타임리프 템플릿 엔지(View)
	- spring-boot-starter(공통) : 스프링 부트 + 스프링 코어 + 로깅
		- spring-boot > spring-core
		- spring-boot-starter-logging > logback, slf4j
- 테스트 라이브러리
	- spring-boot-starter-test
		- junit : 테스트 프레임워트
		- mockito : 목 라이브러리
		- assertj : 테스트 코드를 더 편하게 작성하게 도와주는 라이브러리
		- spring-test : 스프링 통합 테스트 지원


## View 환경설정

- Welcome Page 생성
	- src/main/resources/static/index.html
		- 스프링 부트가 Welcome Page 기능 제공
	- src/main/java/hello.hellospring/controller/HelloController
		- 컨트롤러에서 return 값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리
			- `resources:templates` + {ViewName} + `.html`


## 빌드하고 실행하기
- 터미널 사용
1. `./gradlew build`
2. `cd build/libs`
3. `java -jar hello-spring-0.0.1-SNAPSHOT.jar`


# 스프링 웹 개발 기초

## 정적 컨텐츠
- resources/static/hello-static.html
	- 실행 : http://localhost:8080/hello-static.html

## MVC와 템플릿 엔진
- MVC : Model, View, Controller
	- View : resources/templates/hello-template.html
		- 실행 : http://localhost:8080/hello-mvc?name=spring
![[스크린샷 2023-06-18 오후 5.33.43.png]]

## API
- HelloController에서 `@RespinseBody` 사용
	- HTT의 BODY에 문자 내용을 직접 반환
	- 객체 반환 시 객체가 JSON으로 변환
	- ViewResolver 대신 HttpMessageConverter 동작
	- 기본 문자 처리 : StringHttpMessageConverter
	- 기본 객체 처리 : MappingJackson2HttpMessageConverter
![[스크린샷 2023-06-18 오후 5.37.49.png]]


# 회원 관리 예제 - 백엔드 개발

## 비즈니스 요구사항 정리
![[스크린샷 2023-06-18 오후 5.39.00.png]]


## 회원 도메인과 리포지토리 만들기
- 회원 객체 : main/java/hello.hellospring/domain/Member
- 회원 리포지토리 인터페이스 : repository/MemberRepository
- 회원 리포지토리 메모리 구현체 : repository/MemoryMemberRepository


## 회원 리포지토리 테스트 케이스 작성
- 회원 리포지토리 메모리 구현체 테스트 : test/java/hello.hellospring/repository/MemoryMemberRepositoryTest
	- `@AfterEach` : 테스트가 종료될 때마다 실행
		- DB에 저장된 데이터 삭제되도록 설정


## 회원 서비스 개발 및 테스트
- main/java/hello.hellospring/service/MemberService
	- 회원가입 및 전체 회원 조회
	- 회원 리포지토리의 코드가 회원 서비스 코드를 DI 가능하게 변경
		- test에서 기존 MemoryMemberRepository와 다른 레포지토리를 생성하는 것이 아니라, 같은 레포지토리를 사용하도록
- test/java/hello.hellospring/service/MemberServiceTest
	- given | when | then
	- `@beforeEach` : 각 테스트 실행 전 호출. 테스트가 서로 영향이 없도록 새로운 객체를 생성하고, 새로운 의존관계 맺어줌


# 스프링 빈과 의존관계

## 컴포넌트 스캔과 자동 의존관계 설정
- 회원 컨트롤러가 회원서비스와 회원 리포지토리를 사용할 수 있도록 의존관계 준비
- 회원 컨트롤러에 의존관계 추가
	- controller/MemberController
	- DI(의존성 주입) : 객체 의존관계를 외부에서 넣어줌
		- 필드 주입, setter 주입, 생성자 주입 중 생성자 주입 권장
		- 생성자에 `@Autowired`가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌
			- 생성자가 1개면 생략 가능
		- 이를 위해서 memberService를 스프링 빈으로 등록해야 함

### 스프링 빈 등록 방법
1. 컴포넌트 스캔과 자동 의존관계 설정
	- `@Component` 애노테이션이 있으면 스프링 빈으로 자동 등록
		- `@Cotroller`, `@Service`, `@Repository` 도 자동 등록
	- MemberService에서 `@Service`, `@Autowired` 추가
	- MemoryMemberRepository에서 `@Repository` 추가
	- => 스프링 컨테이너 : memberController -> memberService -> memberRepository

2. 자바 코드로 직접 스프링 빈 등록
- main/java/hello.hellospring/SpringConfig


# 회원 관리 예제 - 웹 MVC 개발

## 회원 웹 기능 - 홈 화면 추가
- 홈 컨트롤러 추가 : controller/HomeController
- 회원 관리용 홈 : resources/templates/home.html
- 컨트롤러가 정적 파일보다 우선순위가 높다.

## 회원 웹 기능 - 등록
- 회원 등록 폼 개발
	- 회원 등록 폼 컨트롤러 : MemberController - createForm
	- 회원 등록 폼 HTML : resources/templates/members/createMemberForm.html
- 회원 등록 컨트롤러
	- 웹 등록 화면에서 데이터를 전달 받을 폼 객체 : controller/MemberForm
	- 회원 컨트롤러에서 회원 실제 등록 기능 : MemberController - create

## 회원 웹 기능 - 조회
- 회원 컨트롤러에서 조회 기능 : MemberController - list
- 회원 리스트 HTML : resources/templates/members/memberList.html


# 스프링 DB 접근 기술

## H2 데이터베이스 설치
https://www.h2database.com/
- 터미널 사용
	- 권한 주기 : `chmod 755 h2.sh`
	- 실행 : `./h2.sh`
	- 데이터베이스 파일 생성
		- `jdbc:h2:~/test` (최초 한 번)
		- `~/test.mv.db` 파일 생성 확인
		- 이후 접속 : `jdbc:h2:tcp://localhost/~/test` 
- 테이블 생성 : sql/ddl.sql
- H2 데이터베이스에 접근하여 주소창의 `100.1.2.3` 부분을 `localhost`로 변경 


## 순수 Jdbc
- build.gradle에 jdbc, h2 데이터베이스 관련 라이브러리 추가
- 스프링 부트 데이터베이스 연결 설정 추가 : resources/application.properties
- Jdbc 리포지토리 구현
	- Jdbc 회원 리포지토리 : repository/JdbcMemberRepository
	- 스프링 설정 변경 : SpringConfig
		- DagaSource : 데이터베이스 커넥션을 획들할 때 사용하는 객체
		- 스프링 부트는 데이터베이스 커넥션 정보를 바탕으로 DataSource를 생성하고 스프링 빈으로 만들어두어, DI를 받을 수 있다.
		- 스프링의 DI를 사용하면 기존 코드를 손대지 않고 설정만으로 구현 클래스 변경 가능

## 스프링 통합 테스트
- 회원 서비스 스프링 통합 테스트 : test/MemberServiceIntegrationTest
	- `@SpringBootTest`: 스프링 컨테이너와 테스트를 함께 실행
	- `@Transactional` : 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후 롤백.
	  -> DB에 데이터가 남지 않아 다음 테스트에 영향을 주지 않는다.

## 스프링 JdbcTemplate
- 스프링 JdbcTemplate 회원 리포지토리 : repository/JdbcTemplateMemberRepository
- JdbcTemplate을 사용하도록 스프링 설정 변경 : SpringConfig

## JPA
- 특징
	- 기존의 반복 코드와 기본적인 SQL을 JPA가 직접 만들어서 실행
	- SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임 전환 가능
	- 개발 생산성을 크게 높일 수 있다.
- build.gradle파일에 JPA, h2 데이터베이스 관련 라이브러리 추가
- 스프링 부트에 JPA 설정 추가 - resources/application.properties
	- `show-sql` : JPA가 생성하는 SQL 출력
	- `ddl-auto` : JPA는 테이블을 자동으로 생성하는 기능 제공
		- `none` : 기능을 끈다.
		- `create` : 엔터티 정보를 바탕으로 테이블 직접 생성해줌
- JPA 엔티티 매핑 : domain/Member
- JPA 회원 리포지토리 : repository/JpaMemberRepository
	- JPA 사용을 위해서 EntityManger를 주입받아야 한다.
- 서비스 계층에 트랜잭션 추가 : service/MemberService
	- `org.springframework.transaction.annotation.Transactional` 사용
	- 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커밋한다. 만약 런타임 예외가 발생하면 롤백
	- JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
- JPA를 사용하도록 스프링 설정 변경 : SpringConfig


## 스프링 데이터 JPA
- 스프링 데이터 JPA 회원 리포지토리 : repository/SpringDataJpaMemberRepository
	- extends로 인해 SpringDataJpa가 구현체를 만들어 등록해준다.
- 스프링 데이터 JPA 회원 리포지토리를 사용하도록 스프링 설정 변경 : SpringConfig
	- `SpringDataJpaMemberRepository`를 스프링 빈으로 자동 등록
![[스크린샷 2023-06-18 오후 6.39.40.png]]
- 스프링 데이터 JPA 제공 기능
	- 인터페이스를 통한 기본적인 CRUD
	- `findByName()`, `findByEmail()`처럼 메서드 이름만으로 조회 기능 제공
	- 페이징 기능 자동 제공

# AOP
- AOP : Aspect Oriented Programming
- 공통 관심 사항 | 핵심 관심 사항 분리
	- 핵심 관심 사항을 깔끔하게 유지할 수 있으며, 변경 필요 시 이 로직만 변경
- 원하는 곳에 공통 관심 사항 적용
- ex) 모든 메소드의 호출 시간을 측정하고 싶다면 메서드에서 하나하나 추가하는 것이 아니라, TimeTraceAop를 등록하여 사용