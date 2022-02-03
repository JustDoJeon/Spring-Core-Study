# 📌 의존 관계 자동 주입

## @Autowired 필드 명, @Qualifier, @Primary

<br>

> 조회 대상 빈이 2개의 오류가 발생시

- @Autowired
- @Qualifier
- @Primary 사용

<br>

> @Autowired 필드 명 매칭

- 타입매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.

<br>

> @Autowired 매칭 정리

- 타입 매칭 , 타입이 하나면 빈이름 아무것도 안보고 들고와서 주입해줌
- 타입 매칭의 결과가 2개 이상일 때 필드명, 파라미터 명으로 빈 이름 매칭

<br>

> @Qualifier

- @Qualifier 추가 구분자를 붙여주는 방법이다.
- 주입시 추가적인 방법을 제공하는 것이지 빈 이름을 변경하는 것은 아니다.

```java

@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy {
}
```

<br>

```java
@Autowired
public OrderServiceImpl(MemberRepository memberRepository,
@Qualifier("mainDiscountPolicy") DiscountPolicy
        discountPolicy){
        this.memberRepository=memberRepository;
        this.discountPolicy=discountPolicy;
        }
```

<br>

> @Qualifier 정리

1. @Qualifier끼리 매칭
2. 빈 이름 매칭
3. NoSuchBeanDefinitionException 예외 발생

<br>

> @Primary 사용

- 우선순위를 정하는 방법이다.
- @Autowired시에 여러 빈이 매칭되면 @Primary가 우선권을 가진다.

```java

@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy {
}

@Component
public class FixDiscountPolicy implements DiscountPolicy {
}
```
- rateDiscountPolicy가 우선권을 가지게된다.
- 참고로, @Qualifier가 @Primary보다 선택권의 범위가 좁기때문에 더 우선순위가 높다. 


<br>


👉 참조 : [인프런](https://www.inflearn.com/)의 **김영한님**강의


<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄

<br>

**개인메모**

