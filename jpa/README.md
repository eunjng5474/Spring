# 스프링 부트와 JPA 활용 - 웹 애플리케이션 개발

## 도메인 분석 설계

### 엔티티 클래스 개발1

```java
@Embeddable
public class Address { }
```

- JPA의 내장 타입 -> `@Embeddable`

```java
@Entity
@Getter @Setter
public class Member {
    // ...

    @Embedded
    private Address address;

    private List<Order> orders = new ArrayList<>();

}
```

- 내장 타입 사용 시 `@Embeddable`, `@Embedded` 중 하나만 써도 되지만 보통 둘 다 많이 쓴다.

```java
@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    private Member member;
}
```

- Order와 Member는 다대일 관계이므로 `@ManyToOne` 추가

- Member에는 `OneToMany` 추가
  
  - `@OneToMany  
    private List<Order> orders = new ArrayList<>();`
