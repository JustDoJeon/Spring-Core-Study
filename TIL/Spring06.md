## 📌 AppConfig 리팩토링 & 할인 정책 적용
<br>

> AppConfig 리팩터링

- 현재 AppConfig를 보면 중복이 있고, 역할에 따른 구현이 잘 보이진않는다.

<br>

```java
// application 전체를 설정하고 구성하기 위한 클래스 중요!!
public class AppConfig {

    /* 리팩토리전
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
    */

    /*
    리팩토링 후 메소드 정리
     */

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    //이제 이 부분의 생성객체만 변경하면 다른 db 사용이 가능한 구조로된것
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }


}
```

- 위 주석과 같이 생성 객체만 변경하면 보기에도 역할이 한눈에 보이고 변경에도 용이함
- FixDiscountPolicy() 를 생성하지만 아래 그림과 같이 변경할땐 객체 생성부분만 해당 할인적용법으로 바꿔주면됨

![appconfig1](https://user-images.githubusercontent.com/52389219/151684395-84dc75b1-f753-4b38-a2e4-be5dbd718401.PNG)


<br>

> 새로운 구조와 할인 정책 적용

- 위에서 쓰인 그림과 같이 RateDiscountPolicy로 변경할것이다!

```java
// application 전체를 설정하고 구성하기 위한 클래스 중요!!
public class AppConfig {

    /* 리팩토리전
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
    */

    /*
    리팩토링 후 메소드 정리
     */

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    //이제 이 부분의 생성객체만 변경하면 다른 db 사용이 가능한 구조로된것
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
          return new RateDiscountPolicy();
    }


}
```

- 구성 영역인 AppConfig의 메소드만 고치면 끗...!!

👉 참조 : [인프런](https://www.inflearn.com/)의 김영한님
**강의 , [남궁성님의 자바의정석] 

<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄
<br>

**개인메모**_
