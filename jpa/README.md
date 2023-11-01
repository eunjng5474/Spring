# 스프링 부트와 JPA 활용 - 웹 애플리케이션 개발

## 도메인 분석 설계

### 엔티티 클래스 개발

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
  
  - `@OneToMany(mappedBy = "member")  
    private List<Order> orders = new ArrayList<>();`
  - `mappedBy` : mapping된 것. 읽기 전용

`@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`

`@DiscriminatorColumn(name = "dtype")`

`public abstract class Item {}`

- Item은 구현체를 가지게 하기 위해 추상 클래스로 만든다

- InheritanceType
  
  - `JOINED` : 가장 정규화된 스타일
  
  - `SINGLE_TABLE` :  한 테이블에 모두 넣음
  
  - `TABLE_PER_CLASS` : n개의 테이블로 나누기

- DiscriminatorColumn : db에서 구분하기 위함
  
  - Book - `@DiscriminatorValue("B")`
  
  - Movie - `@DiscriminatorValue("M")` 
  
  - Album - `@DiscriminatorValue("A")`

- DeliveryStatus -> enum으로
  
  - Delivery에서 `@Enumerated` 추가
  
  - `@Enumerated(EnumType.STRING)  
    private DeliveryStatus status; // READY, COMP`
    
    - EnumType - ordinal : 숫자 형태
      
      - 중간에 값이 생기면 인덱스 변경됨 => 사용X
    
    - STRING으로 쓰기!

- Category

```java
@ManyToMany
@JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id"))
private List<Item> items = new ArrayList<>();



// 셀프로 양방향 연결관계 
@ManyToOne
@JoinColumn(name = "parent_id")
private Category parent;

@OneToMany(mappedBy = "parent")
private List<Category> child = new ArrayList<>();
```

### 엔티티 설계시 주의점

- `@ManyToOne(fetch = FetchType.EAGER)`
  
  - order를 조회할 때 member를 join해서 한 번에 가져옴
  
  - 따라서 지연로딩으로 바꾸기! 
  
  - 연관된 엔티티를 함께 DB에서 조회해야 하면, fetch join 사용
  
  - `OneToMany`는 default가 LAZY이지만, `XToOne`은 default가 EAGER

- 컬렉션은 필드에서 바로 초기화 하는 것이 안전

```java
// ...
private List<Order> orders;

public Member() {
    orders = new ArrayList<>();
}
// 이것보다는 아래가 더 안전하다 


private List<Order> orders = new ArrayList<>();
```

- `cascade = CascadeType.ALL`
  
  - Order에서 delivery의 OneToOne annotation에 추가
  
  - 원래 각각 persist 해줘야 하지만. 위 내용 추가할 경우 order만 persist하면 delivery도 persist된다.

```java
    // == 연관관계 메서드 == //
    /*
    public void setMember(Member member) {
        this.member = member;
    }

    public static void main(String[] args) {
        Member member = new Member();
        Order order = new Order();

        member.getOrders().add(order);
        order.setMember(member);
    } 
    */

    // ->
        public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }


        public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
```

- -> 양방향일 때 원자적으로 한 코드로 세팅

## 회원 도메인 개발

### Repository

- `@PersistenceContext  
  private EntityManager em;`
  
  - 스피링이 EntityManager 주입   

### Service

- lombok

- `@AllArgsConstructor` : 모든 필드를 가지고 아래 생성자 만들어줌
  
  - `public MemberService(MemberRepository memberRepository) {  
    this.memberRepository = memberRepository;  
    }`

- `RequiredArgsConstructor` : final이 붙은 필드만 생성자 만들어줌
  
  - Spring boot 사용시 MemberRepository에서도 em에 대해 사용 가능



## 상품 도메인 개발

- 데이터를 가지는 쪽에 비즈니스 로직을 만드는 게 응집력이 있어 좋다.
  -> Item entity에 stockQuantity가 있기 때문에 Item에 핵심 비즈니스 로직 작성

- NotEnoughStockException
  
  - extends RuntimeException 후 ctrl + o -> override



## 주문 도메인 개발

- 생성 메서드를 통해 추후 생성 부분을 수정할 때 해당 메서드만 변경하면 된다.
  
  - 다른 스타일의 생성을 모두 막아야 한다.
  
  - `protected OrderItem() {}` 추가 혹은 `@NoArgsConstructor(access = AccessLevel.PROTECTED)` annotation 추가 

- OrderService
  
  - cascade로 인해 order만 저장해도 orderItem과 delivery가 persist된다.
  
  - 다른 곳에서도 참조하는 것이라면 cascade 사용 주의
    (Order만 orderItem과 delivery를 사용하고, persist하는 life cycle이 동일하기 때문에 cascade를 사용했다.)



### 주문 검색 기능 개발
