# 📌 의존 관계 자동 주입

## 다양한 의존관계 주입 방법

- 생성자 주입
- 수정자 주입(setter 주입)
- 필드 주입
- 일반 메서드 주입

<br>

> 생성자 주입

- 이름 그대로 생성자를 통해서 의존 관계를 주입 받는 방법이다.
- 생성자 호출 시점에 딱 한번만 호출되는것이 보장된다.
- 항상은 아니지만 불변, 필수 의존관계에 사용된다.

```java

@Component
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
}
```

<br>

> 수정자 주입

- setter라 불리는 필드의 값을 변경하는 수정자 메서드 통해서 의존관계를 주입하는 방법이다.
- 주로 선택, 변경 가능성이 있는 의존관계에 사용
- 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.

```java

@Component
public class OrderServiceImpl implements OrderService {
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}
```

- @Autowired의 기본 동작은 주입할 대상이 없으면 오류가 발생하는데, 주입할 대상이 없어도 동작하게 하려면 @Autowired(required = false)로 지정하면 된다.

> 필드 주입

- 내가 중견SI 다닐때 모든 코드가 필드주입이었음..
- 이름 그대로 필드에 바로 주입하는 방법이다.
- 코드가 간결하지만 외부에서 변경이 불가능하기때문에 테스트 하기가 힘들다.
- DI 프레임워크가 없으면 아무것도 할 수 없다.
- 주로 실제코드와 상관없는 테스트코드에서만 사용된다.

```java

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DiscountPolicy discountPolicy;
}
```

<br>

> 일반 메서드 주입

- 일반 메소드를 통해서 주입 받을 수 있다.
- 한번에 여러 필드를 주입 받을순 있지만 일반적으로 잘 사용하지 않는다.

```java

@Component
public class OrderServiceImpl implements OrderService {
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy
            discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
}
```

#### 생성자 주입을 선택해야하는 이유
- 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다.
- 그러므로 불변해야함 
- 수정자 주입을 사용하면, setXxx메서드를 public으로 열어두어야한다.
- 누군가 실수로 변경할 수도 있고, 변경하면 안되는 메서드를 열어두는것은 좋은 설계 방법이 아니다.
- 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이없다.


👉 참조 : [인프런](https://www.inflearn.com/)의 **김영한님**강의 , [남궁성님의 자바의정석]


<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄

<br>

**개인메모**

