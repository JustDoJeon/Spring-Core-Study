# ๐ ์น ์ดํ๋ฆฌ์ผ์ด์๊ณผ ์ฑ๊ธํค 2

## ์ฑ๊ธํค ๋ฐฉ์์ ์ฃผ์์ 

- ๊ฐ์ฒด ์ธ์คํด์ค๋ฅผ ํ๋๋ง ์์ฑํด์ ๊ณต์ ํ๋ ๋ฐฉ์์ ์ฌ๋ฌ ํด๋ผ์ด์ธํธ๊ฐ ํ๋์ ๊ฐ์ฒด ์ธ์คํด์ค๋ฅผ ๊ณต์ ํ๊ธฐ ๋๋ฌธ์ 
- ์ํ๋ฅผ ์ ์ง(stateful) ํ๊ฒ ์ค๊ณํ๋ฉด ์๋๋ค.
- ๋ฌด์ํ(stateless)๋ก ์ค๊ณํด์ผํ๋ค.
    - ํน์  ํด๋ผ์ด์ธํธ์ ์์กด์ ์ธ ํ๋๊ฐ ์์ผ๋ฉด ์๋๋ค.
    - ํน์  ํด๋ผ์ด์ธํธ๊ฐ ๊ฐ์ ๋ณ๊ฒฝํ  ์ ์๋ ํ๋๊ฐ ์์ผ๋ฉด ์๋๋ค.
    - ๊ฐ๊ธ์  ์ฝ๊ธฐ๋ง ๊ฐ๋ฅํด์ผ ํ๋ค.
    - ํ๋ ๋์  ์๋ฐ์์ ๊ณต์ ๋์ง์๋ ์ง์ญ๋ณ์, ํ๋ผ๋ฏธํฐ, ThreadLocal ๋ฑ์ ์ฌ์ฉํด์ผํ๋ค.
    

### ์ํ๋ฅผ ์ ์งํ  ๊ฒฝ์ฐ ๋ฐ์ํ๋ ๋ฌธ์ ์  ์์

![์ฑ๊ธํค ๋ฌธ์ ์ 1](https://user-images.githubusercontent.com/52389219/152277974-b744422e-1f40-40aa-8401-1f6beb5f4a5f.PNG)


> ๋ฌธ์ ์  ์ถ๋ ฅ๊ฐ 
- ์ ์ A์ ์ฃผ๋ฌธ๊ธ์ก์ธ 10000์์ด ํธ์ถ๋์ด์ผํ๋๋ฐ
- 20000์์ด ํธ์ถ๋จ
- ๊ณต์ ํ๋๋ฅผ ์กฐ์ฌํด์ ์ฌ์ฉํด์ผํจ...

### ๋ฌธ์  ํด๊ฒฐ

> ๋ณ๊ฒฝ ์  StatefulService.java
```java
package spring.core.singleton;

public class StatefulService {

    private int price; //์ํ๋ฅผ ์ ์งํ๋ ํ๋

    public void order(String name, int price){
        System.out.println( "name = " + name + " price = " + price );
        this.price = price; // ์ฌ๊ธฐ๊ฐ ๋ฌธ์ !
    }

    public int getPrice() {
        return price;
    }
}

```
> ๋ณ๊ฒฝ ํ StatfulService.java
```java
package spring.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A์ฌ์ฉ์ 10000์ ์ฃผ๋ฌธ
       int priceA =  statefulService1.order("userA",10000);
        //ThreadB : B์ฌ์ฉ์ 20000์ ์ฃผ๋ฌธ
        int priceB = statefulService1.order("userB",20000);

        //ThreadA : ์ฌ์ฉ์A ์ฃผ๋ฌธ ๊ธ์ก ์กฐํ
//        int price = statefulService1.getPrice();
        System.out.println(" price = " + priceA);

//        org.assertj.core.api.Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
```

- ์ถ๋ ฅ ํ์ธ

- <br>

![์ฑ๊ธํค ๋ฌธ์ ์  2](https://user-images.githubusercontent.com/52389219/152278252-eb8e08ee-72ef-4969-ae81-761cce1cf650.PNG)

๐ ์ฐธ์กฐ : [์ธํ๋ฐ](https://www.inflearn.com/)์ **๊น์ํ๋**๊ฐ์ , [๋จ๊ถ์ฑ๋์ ์๋ฐ์์ ์]


<br>
๐ ๊ฐ์ธ ๊ณต๋ถ ๊ธฐ๋ก์ฉ ๋ธ๋ก๊ทธ์๋๋ค. ์ค๋ฅ๋ ํ๋ฆฐ ๋ถ๋ถ์ด ์์ ๊ฒฝ์ฐ 
์ธ์ ๋ ์ง ๋๊ธ ํน์ ๋ฉ์ผ๋ก ์ง์ ํด์ฃผ์๋ฉด ๊ฐ์ฌํ๊ฒ ์ต๋๋ค! ๐

<br>

**๊ฐ์ธ๋ฉ๋ชจ**

