# ๐ ์์กด ๊ด๊ณ ์๋ ์ฃผ์

## @Autowired ํ๋ ๋ช, @Qualifier, @Primary

<br>

> ์กฐํ ๋์ ๋น์ด 2๊ฐ์ ์ค๋ฅ๊ฐ ๋ฐ์์

- @Autowired
- @Qualifier
- @Primary ์ฌ์ฉ

<br>

> @Autowired ํ๋ ๋ช ๋งค์นญ

- ํ์๋งค์นญ์ ์๋ํ๊ณ , ์ด๋ ์ฌ๋ฌ ๋น์ด ์์ผ๋ฉด ํ๋ ์ด๋ฆ, ํ๋ผ๋ฏธํฐ ์ด๋ฆ์ผ๋ก ๋น ์ด๋ฆ์ ์ถ๊ฐ ๋งค์นญํ๋ค.

<br>

> @Autowired ๋งค์นญ ์ ๋ฆฌ

- ํ์ ๋งค์นญ , ํ์์ด ํ๋๋ฉด ๋น์ด๋ฆ ์๋ฌด๊ฒ๋ ์๋ณด๊ณ  ๋ค๊ณ ์์ ์ฃผ์ํด์ค
- ํ์ ๋งค์นญ์ ๊ฒฐ๊ณผ๊ฐ 2๊ฐ ์ด์์ผ ๋ ํ๋๋ช, ํ๋ผ๋ฏธํฐ ๋ช์ผ๋ก ๋น ์ด๋ฆ ๋งค์นญ

<br>

> @Qualifier

- @Qualifier ์ถ๊ฐ ๊ตฌ๋ถ์๋ฅผ ๋ถ์ฌ์ฃผ๋ ๋ฐฉ๋ฒ์ด๋ค.
- ์ฃผ์์ ์ถ๊ฐ์ ์ธ ๋ฐฉ๋ฒ์ ์ ๊ณตํ๋ ๊ฒ์ด์ง ๋น ์ด๋ฆ์ ๋ณ๊ฒฝํ๋ ๊ฒ์ ์๋๋ค.

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

> @Qualifier ์ ๋ฆฌ

1. @Qualifier๋ผ๋ฆฌ ๋งค์นญ
2. ๋น ์ด๋ฆ ๋งค์นญ
3. NoSuchBeanDefinitionException ์์ธ ๋ฐ์

<br>

> @Primary ์ฌ์ฉ

- ์ฐ์ ์์๋ฅผ ์ ํ๋ ๋ฐฉ๋ฒ์ด๋ค.
- @Autowired์์ ์ฌ๋ฌ ๋น์ด ๋งค์นญ๋๋ฉด @Primary๊ฐ ์ฐ์ ๊ถ์ ๊ฐ์ง๋ค.

```java

@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy {
}

@Component
public class FixDiscountPolicy implements DiscountPolicy {
}
```
- rateDiscountPolicy๊ฐ ์ฐ์ ๊ถ์ ๊ฐ์ง๊ฒ๋๋ค.
- ์ฐธ๊ณ ๋ก, @Qualifier๊ฐ @Primary๋ณด๋ค ์ ํ๊ถ์ ๋ฒ์๊ฐ ์ข๊ธฐ๋๋ฌธ์ ๋ ์ฐ์ ์์๊ฐ ๋๋ค. 


<br>


๐ ์ฐธ์กฐ : [์ธํ๋ฐ](https://www.inflearn.com/)์ **๊น์ํ๋**๊ฐ์


<br>
๐ ๊ฐ์ธ ๊ณต๋ถ ๊ธฐ๋ก์ฉ ๋ธ๋ก๊ทธ์๋๋ค. ์ค๋ฅ๋ ํ๋ฆฐ ๋ถ๋ถ์ด ์์ ๊ฒฝ์ฐ 
์ธ์ ๋ ์ง ๋๊ธ ํน์ ๋ฉ์ผ๋ก ์ง์ ํด์ฃผ์๋ฉด ๊ฐ์ฌํ๊ฒ ์ต๋๋ค! ๐

<br>

**๊ฐ์ธ๋ฉ๋ชจ**

