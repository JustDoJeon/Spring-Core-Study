<br>

## ๐ ๊ฐ์ฒด ์งํฅ ์๋ฆฌ ์ ์ฉ

> ์๋ก์ด ํ ์ธ ์ ์ฑ์ ํ์ฅํด๋ณด์.

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


> ์๋ก์ด ํ ์ธ ์ ์ฑ์ ์ ์ฉํ๋ ค๋ฉด OrderServiceImpl์ ์ฝ๋๋ฅผ ๊ณ ์ณ์ผํ๋ค.

```java

//OrderService์ ์์ฅ์์  ํ ์ธ์ ๋ ๋ชจ๋ฅด๊ฒ ์ผ๋ ๋๊ฐ ๋์ ธ์ฃผ๊ธฐ๋งํด์ค (SRP)
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
- ์ง๊ธ ์ฝ๋๋ ๊ธฐ๋ฅ์ ํ์ฅํด์ ๋ณ๊ฒฝํ๋ฉด, ํด๋ผ์ด์ธํธ ์ฝ๋์ ์ํฅ์ ์ค๋ค! ๋ฐ๋ผ์ OCP๋ฅผ ์๋ฐํ๋ค.

> ๊ธฐ๋ํ๋ ์์กด๊ด๊ณ
![์๋ก์ด์ ์ฑ2](https://user-images.githubusercontent.com/52389219/151577241-4a6cb2b3-1778-49ac-b7df-2a02f41fde2e.PNG)

<br>

>์ค์  ์์กด๊ด๊ณ
![์๋ก์ด์ ์ฑ3](https://user-images.githubusercontent.com/52389219/151577245-69a3938f-ff8e-4a66-b92a-c315178331ca.PNG)

<br>
 
>์ ์ฑ ๋ณ๊ฒฝ
![์๋ก์ด์ ์ฑ4](https://user-images.githubusercontent.com/52389219/151577247-a7190987-d00d-4d19-bf94-35f212b70bdd.PNG)


- ์ ์ฑ๋ณ๊ฒฝ์ , OrderServiceImpl์ ์์ค์ฝ๋๋ ๋ณ๊ฒฝํด์ผํ๋ฏ๋ก OCP ์๋ฐ

<BR>

> ์ธํฐํ์ด์ค์๋ง ์์กดํ๋๋ก ์ค๊ณ๋ฅผ ๋ณ๊ฒฝํ์

![์๋ก์ด์ ์ฑ5](https://user-images.githubusercontent.com/52389219/151577248-5d2d70e9-af8f-42dc-8f96-4303b3d9a2a5.PNG)

```java
//OrderService์ ์์ฅ์์  ํ ์ธ์ ๋ ๋ชจ๋ฅด๊ฒ ์ผ๋ ๋๊ฐ ๋์ ธ์ฃผ๊ธฐ๋งํด์ค (SRP)
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

- DIP์๋ฐ -> ์ถ์์๋ง ์์กดํ๋๋ก ๋ณ๊ฒฝ (์ธํฐํ์ด์ค์๋ง ์์กด)

<BR>

- ์์ ์ฝ๋๋ฅผ ์คํํ๋ฉด ์ธํฐํ์ด์ค๋ง ์์กดํ๊ณ ๋ ์๋๋ฐ ๊ตฌํ์ฒด๊ฐ์์ด์ null ๋ธ!
- ๋ค์ ๋ธ๋ก๊ทธ์ ์ฃผ์์ ์ด์ฉํด์ ํด๊ฒฐํด๋ณด๊ฒ ๋ค! 





๐ ์ฐธ์กฐ : [์ธํ๋ฐ](https://www.inflearn.com/)์ ๊น์ํ๋
๊ฐ์ , [๋จ๊ถ์ฑ๋์ ์๋ฐ์์ ์] , [์ํค๋ฐฑ๊ณผ](https://ko.wikipedia.org/wiki/SOLID_%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%EC%84%A4%EA%B3%84)

<br>
๐ ๊ฐ์ธ ๊ณต๋ถ ๊ธฐ๋ก์ฉ ๋ธ๋ก๊ทธ์๋๋ค. ์ค๋ฅ๋ ํ๋ฆฐ ๋ถ๋ถ์ด ์์ ๊ฒฝ์ฐ 
์ธ์ ๋ ์ง ๋๊ธ ํน์ ๋ฉ์ผ๋ก ์ง์ ํด์ฃผ์๋ฉด ๊ฐ์ฌํ๊ฒ ์ต๋๋ค! ๐
<br>

**๊ฐ์ธ๋ฉ๋ชจ**_
