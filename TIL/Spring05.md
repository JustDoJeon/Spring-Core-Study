## 📌 AppConfig를 통한 관심사 분리
<br>

> AppConfig 클래스

- AppConfig 클래스를 통해 담당기능을 실행하는 책임만주고 관심사를 확실하게 분리했다.
- 애플리케이션의 전체 동작방식을 구성(config )하기 위해, 구현객체를 생성하고, 연결하는 책임을 가지는 별도의 클래스다.

<br>

```java
// application 전체를 설정하고 구성하기 위한 클래스 중요!!
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
```

<br>

- AppConfig는 실제 동작에 필요한 구현 객체를 생성한다.
- 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입(연결) 해준다.

### MemberServiceImpl - 생성자 주입

```java
package spring.core.member;

public class MemberServiceImpl implements MemberService {

    // 가입을하고 회원을 찾으려면 멤버리포지토리가 필요함
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // AppConfig 생성과 함께 -> DIP를 지키게됨 구체적인 내용은 모르고 (Appconfig에서 생성함) 인터페이스만 의존
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
```
- 주석(변경 전 코드)을 하고 새로 생성자 주입 코드를 입력했다.
- 이젠 MemberRepository 인터페이스만 의존한다.
- MemberServiceImpl 입장에선 생성자를 통해 어떤 구현 객체가 들어올지 알수없음
- 어떤 구현 객체를 주입 할 지는 오직 외부(Appconfig)를 통해 결정된다.

> 클래스 다이어그램

![app1](https://user-images.githubusercontent.com/52389219/151671087-2ca20225-e58d-466c-b19d-9bfad7071051.PNG)

- 객체의 생성과 연결은 AppConfig가 담당
- DIP 완성

<br>

### OrderServiceImpl - 생성자 주입

```java
//OrderService의 입장에선 할인은 난 모르겠으니 너가 던져주기만해줘 (SRP)
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

//    private DiscountPolicy discountPolicy;

    //Appconfig로 생성자 주입
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        int disCountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, disCountPrice);
    }
}
```
> AppConfig 실행

- 사용 클래스 : MemberApp
```java
public class MemberApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        
        //AppConfig 설정 이후 위 코드로 변경
        //MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "전도현", Grade.VIP);
        memberService.join(member);

        Member findeMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findeMember.getName());
    }

}
```

<br>

> 사용 클래스 : OrderApp
```java
public class OrderApp {

    public static void main(String[] args) {
        //AppConfig 이후
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();


        //MemberService memberService = new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA",10000);

        System.out.println("order = " + order);

    }
}
```



👉 참조 : [인프런](https://www.inflearn.com/)의 김영한님
**강의 , [남궁성님의 자바의정석] ,** [위키백과](https://ko.wikipedia.org/wiki/SOLID_%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%EC%84%A4%EA%B3%84)

<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄
<br>

**개인메모**_
