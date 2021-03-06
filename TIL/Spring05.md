## ๐ AppConfig๋ฅผ ํตํ ๊ด์ฌ์ฌ ๋ถ๋ฆฌ
<br>

> AppConfig ํด๋์ค

- AppConfig ํด๋์ค๋ฅผ ํตํด ๋ด๋น๊ธฐ๋ฅ์ ์คํํ๋ ์ฑ์๋ง์ฃผ๊ณ  ๊ด์ฌ์ฌ๋ฅผ ํ์คํ๊ฒ ๋ถ๋ฆฌํ๋ค.
- ์ ํ๋ฆฌ์ผ์ด์์ ์ ์ฒด ๋์๋ฐฉ์์ ๊ตฌ์ฑ(config )ํ๊ธฐ ์ํด, ๊ตฌํ๊ฐ์ฒด๋ฅผ ์์ฑํ๊ณ , ์ฐ๊ฒฐํ๋ ์ฑ์์ ๊ฐ์ง๋ ๋ณ๋์ ํด๋์ค๋ค.

<br>

```java
// application ์ ์ฒด๋ฅผ ์ค์ ํ๊ณ  ๊ตฌ์ฑํ๊ธฐ ์ํ ํด๋์ค ์ค์!!
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

- AppConfig๋ ์ค์  ๋์์ ํ์ํ ๊ตฌํ ๊ฐ์ฒด๋ฅผ ์์ฑํ๋ค.
- ์์ฑํ ๊ฐ์ฒด ์ธ์คํด์ค์ ์ฐธ์กฐ๋ฅผ ์์ฑ์๋ฅผ ํตํด์ ์ฃผ์(์ฐ๊ฒฐ) ํด์ค๋ค.

### MemberServiceImpl - ์์ฑ์ ์ฃผ์

```java
package spring.core.member;

public class MemberServiceImpl implements MemberService {

    // ๊ฐ์์ํ๊ณ  ํ์์ ์ฐพ์ผ๋ ค๋ฉด ๋ฉค๋ฒ๋ฆฌํฌ์งํ ๋ฆฌ๊ฐ ํ์ํจ
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // AppConfig ์์ฑ๊ณผ ํจ๊ป -> DIP๋ฅผ ์งํค๊ฒ๋จ ๊ตฌ์ฒด์ ์ธ ๋ด์ฉ์ ๋ชจ๋ฅด๊ณ  (Appconfig์์ ์์ฑํจ) ์ธํฐํ์ด์ค๋ง ์์กด
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
- ์ฃผ์(๋ณ๊ฒฝ ์  ์ฝ๋)์ ํ๊ณ  ์๋ก ์์ฑ์ ์ฃผ์ ์ฝ๋๋ฅผ ์๋ ฅํ๋ค.
- ์ด์   MemberRepository ์ธํฐํ์ด์ค๋ง ์์กดํ๋ค.
- MemberServiceImpl ์์ฅ์์  ์์ฑ์๋ฅผ ํตํด ์ด๋ค ๊ตฌํ ๊ฐ์ฒด๊ฐ ๋ค์ด์ฌ์ง ์์์์
- ์ด๋ค ๊ตฌํ ๊ฐ์ฒด๋ฅผ ์ฃผ์ ํ  ์ง๋ ์ค์ง ์ธ๋ถ(Appconfig)๋ฅผ ํตํด ๊ฒฐ์ ๋๋ค.

> ํด๋์ค ๋ค์ด์ด๊ทธ๋จ

![app1](https://user-images.githubusercontent.com/52389219/151671087-2ca20225-e58d-466c-b19d-9bfad7071051.PNG)

- ๊ฐ์ฒด์ ์์ฑ๊ณผ ์ฐ๊ฒฐ์ AppConfig๊ฐ ๋ด๋น
- DIP ์์ฑ

<br>

### OrderServiceImpl - ์์ฑ์ ์ฃผ์

```java
//OrderService์ ์์ฅ์์  ํ ์ธ์ ๋ ๋ชจ๋ฅด๊ฒ ์ผ๋ ๋๊ฐ ๋์ ธ์ฃผ๊ธฐ๋งํด์ค (SRP)
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

//    private DiscountPolicy discountPolicy;

    //Appconfig๋ก ์์ฑ์ ์ฃผ์
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
> AppConfig ์คํ

- ์ฌ์ฉ ํด๋์ค : MemberApp
```java
public class MemberApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        
        //AppConfig ์ค์  ์ดํ ์ ์ฝ๋๋ก ๋ณ๊ฒฝ
        //MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "์ ๋ํ", Grade.VIP);
        memberService.join(member);

        Member findeMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findeMember.getName());
    }

}
```

<br>

> ์ฌ์ฉ ํด๋์ค : OrderApp
```java
public class OrderApp {

    public static void main(String[] args) {
        //AppConfig ์ดํ
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



๐ ์ฐธ์กฐ : [์ธํ๋ฐ](https://www.inflearn.com/)์ ๊น์ํ๋
**๊ฐ์ , [๋จ๊ถ์ฑ๋์ ์๋ฐ์์ ์] ,** [์ํค๋ฐฑ๊ณผ](https://ko.wikipedia.org/wiki/SOLID_%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%EC%84%A4%EA%B3%84)

<br>
๐ ๊ฐ์ธ ๊ณต๋ถ ๊ธฐ๋ก์ฉ ๋ธ๋ก๊ทธ์๋๋ค. ์ค๋ฅ๋ ํ๋ฆฐ ๋ถ๋ถ์ด ์์ ๊ฒฝ์ฐ 
์ธ์ ๋ ์ง ๋๊ธ ํน์ ๋ฉ์ผ๋ก ์ง์ ํด์ฃผ์๋ฉด ๊ฐ์ฌํ๊ฒ ์ต๋๋ค! ๐
<br>

**๊ฐ์ธ๋ฉ๋ชจ**_
