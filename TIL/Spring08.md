# ๐ ์คํ๋ง์ผ๋ก ์ ํํ๊ธฐ
<br>

> ๊ธฐ์กด ์งํํด์๋ ํ๋ก์ ํธ ์์ค ๊ธฐ๋ฐ


## AppConfig ์คํ๋ง ๊ธฐ๋ฐ์ผ๋ก ๋ณ๊ฒฝ

```java
// application ์ ์ฒด๋ฅผ ์ค์ ํ๊ณ  ๊ตฌ์ฑํ๊ธฐ ์ํ ํด๋์ค ์ค์!!
@Configuration
public class AppConfig {

    /* ๋ฆฌํฉํ ๋ฆฌ์ 
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
    */

    /*
    ๋ฆฌํฉํ ๋ง ํ ๋ฉ์๋ ์ ๋ฆฌ
     */
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //์ด์  ์ด ๋ถ๋ถ์ ์์ฑ๊ฐ์ฒด๋ง ๋ณ๊ฒฝํ๋ฉด ๋ค๋ฅธ db ์ฌ์ฉ์ด ๊ฐ๋ฅํ ๊ตฌ์กฐ๋ก๋๊ฒ
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
```

#### - AppConfig์ ์ค์ ์ ๊ตฌ์ฑํ๋ค๋ ๋ป์ @Configuration์ ๋ถ์ฌ์ค๋ค.
#### - ๊ฐ ๋ฉ์๋์ @Bean์ ๋ถ์ฌ์ค! ์ด๋ ๊ฒํ๋ฉด ์คํ๋ง ์ปจํ์ด๋์ ๋น์ผ๋ก ๋ฑ๋ก๋๋ค.

<br>


### โ๏ธย   MemberApp์ ์คํ๋ง ์ปจํ์ด๋ ์ ์ฉ

```java
public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();

//        MemberService memberService = appConfig.memberService();

        //์ด๋ ๊ฒ ์ค์ ํจ์ผ๋ก์จ ํด๋นํด๋์ค๋ฅผ ์คํ๋ง์ด ๋น์ผ๋ก ๋ฑ๋กํด์ ๊ด๋ฆฌํ๋ค.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //ํ๋ผ๋ฏธํฐ 1: ์ฐพ์ ๋ฉ์๋ ์ด๋ฆ, ํ๋ผ๋ฏธํฐ2 : ํด๋์ค
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        //AppConfig ์ค์  ์ดํ ์ ์ฝ๋๋ก ๋ณ๊ฒฝ
        //MemberService memberService = new MemberServiceImpl();


        Member member = new Member(1L, "์ ๋ํ", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());


    }

}
```

<br>

### MemberApp์ ์คํ ๋ก๊ทธ ํ๋ฉด 

![์คํ๋ง์ ํ์ฌ์ง1](https://user-images.githubusercontent.com/52389219/151702704-79dab7ad-9ab1-4ab7-85a5-8ec76b64a04a.PNG)

<br>

### โ๏ธย   OrderApp์ ์คํ๋ง ์ปจํ์ด๋ ์ ์ฉ

```java
public class OrderApp {

    public static void main(String[] args) {
        //AppConfig ์ดํ
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();


        //MemberService memberService = new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA",10000);

        System.out.println("order = " + order);

    }
}
```

<br>

### โ๏ธย ์คํ๋ง ์ปจํ์ด๋๋?

![img.png](img.png)

#### > ์คํ๋ง ์ปจํ์ด๋๋ ์๋ฐ ๊ฐ์ฒด์ ์๋ช์ฃผ๊ธฐ๋ฅผ ๊ด๋ฆฌํ๋ฉฐ, ์์ฑ๋ ์๋ฐ ๊ฐ์ฒด๋ค์๊ฒ ์ถ๊ฐ์ ์ธ ๊ธฐ๋ฅ์ ์ ๊ณตํ๋ ์ญํ ์ ํ๋ค.
#### > ์๋ฐ ๊ฐ์ฒด๋ฅผ ์คํ๋ง์์  Bean ์ด๋ผ๊ณ  ๋ถ๋ฅด๊ณ  IoC์ DI์ ์๋ฆฌ๊ฐ ์ด ์คํ๋ง ์ปจํ์ด๋์ ์ ์ฉ๋๋ค.
#### > new ์ฐ์ฐ์์ ์ธํฐํ์ด์ค ํธ์ถ, ํฉํ ๋ฆฌ ํธ์ถ ๋ฐฉ์์ผ๋ก ๊ฐ์ฒด๋ฅผ ์์ฑ,์๋ฉธ ์ํฌ์์๋๋ฐ ์คํ๋ง ์ปจํ์ด๋๊ฐ ์ด ์ญํ ์ ๋์ ํด์ค๋ค.
#### > ์คํ๋ง ์ปจํ์ด๋ ์์ ๋น ์ ์ฅ์๊ฐ ์๊ณ  ๊ทธ ์์ ๋น์ด ์๋ค๊ณ  ์๊ฐํ๋ฉด๋๋ค.








๐ ์ฐธ์กฐ : [์ธํ๋ฐ](https://www.inflearn.com/)์ ๊น์ํ๋
**๊ฐ์ , [๋จ๊ถ์ฑ๋์ ์๋ฐ์์ ์] ,[๋ธ๋ก๊ทธ ์ฐธ๊ณ ](https://steady-coding.tistory.com/459)

<br>
๐ ๊ฐ์ธ ๊ณต๋ถ ๊ธฐ๋ก์ฉ ๋ธ๋ก๊ทธ์๋๋ค. ์ค๋ฅ๋ ํ๋ฆฐ ๋ถ๋ถ์ด ์์ ๊ฒฝ์ฐ 
์ธ์ ๋ ์ง ๋๊ธ ํน์ ๋ฉ์ผ๋ก ์ง์ ํด์ฃผ์๋ฉด ๊ฐ์ฌํ๊ฒ ์ต๋๋ค! ๐
<br>

**๊ฐ์ธ๋ฉ๋ชจ**_
์คํ๋ง ์ปจํ์ด๋ ์ข๋ฅ์ ๋ํด ์ถ๊ฐ ํ์ต๋ฐ ์ ๋ฆฌ๊ฐ ํ์ํ  ๊ฒ ๊ฐ๋ค.