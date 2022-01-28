<br>

## 📌 객체 지향 원리 적용

> 새로운 할인 정책을 확장해보자.

```java

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent/100;
        }else{
            return 0;
        }
    }
}

```

<br>


> 새로운 할인 정책을 적용하려면 OrderServiceImpl의 코드를 고쳐야한다.

```java

//OrderService의 입장에선 할인은 난 모르겠으니 너가 던져주기만해줘 (SRP)
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        int disCountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, disCountPrice);
    }
}

```
- 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다! 따라서 OCP를 위반한다.

> 기대했던 의존관계
![새로운정책2](https://user-images.githubusercontent.com/52389219/151577241-4a6cb2b3-1778-49ac-b7df-2a02f41fde2e.PNG)

<br>

>실제 의존관계
![새로운정책3](https://user-images.githubusercontent.com/52389219/151577245-69a3938f-ff8e-4a66-b92a-c315178331ca.PNG)

<br>
 
>정책 변경
![새로운정책4](https://user-images.githubusercontent.com/52389219/151577247-a7190987-d00d-4d19-bf94-35f212b70bdd.PNG)


- 정책변경시 , OrderServiceImpl의 소스코드도 변경해야하므로 OCP 위반

<BR>

> 인터페이스에만 의존하도록 설계를 변경하자

![새로운정책5](https://user-images.githubusercontent.com/52389219/151577248-5d2d70e9-af8f-42dc-8f96-4303b3d9a2a5.PNG)

```java
//OrderService의 입장에선 할인은 난 모르겠으니 너가 던져주기만해줘 (SRP)
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        int disCountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, disCountPrice);
    }
}
```

- DIP위반 -> 추상에만 의존하도록 변경 (인터페이스에만 의존)

<BR>

- 위의 코드를 실행하면 인터페이스만 의존하고는 있는데 구현체가없어서 null 뜸!
- 다음 블로그에 주입을 이용해서 해결해보겠다! 





👉 참조 : [인프런](https://www.inflearn.com/)의 김영한님
강의 , [남궁성님의 자바의정석] , [위키백과](https://ko.wikipedia.org/wiki/SOLID_%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%EC%84%A4%EA%B3%84)

<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄
<br>

**개인메모**_
