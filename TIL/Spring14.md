# 📌 웹 어플리케이션과 싱글톤 2

## 싱글톤 방식의 주의점

- 객체 인스턴스를 하나만 생성해서 공유하는 방식은 여러 클라이언트가 하나의 객체 인스턴스를 공유하기 때문에 
- 상태를 유지(stateful) 하게 설계하면 안된다.
- 무상태(stateless)로 설계해야한다.
    - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
    - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
    - 가급적 읽기만 가능해야 한다.
    - 필드 대신 자바에서 공유되지않는 지역변수, 파라미터, ThreadLocal 등을 사용해야한다.
    

### 상태를 유지할 경우 발생하는 문제점 예시

![싱글톤 문제점1](https://user-images.githubusercontent.com/52389219/152277974-b744422e-1f40-40aa-8401-1f6beb5f4a5f.PNG)


> 문제점 출력값 
- 유저A의 주문금액인 10000원이 호출되어야하는데
- 20000원이 호출됨
- 공유필드를 조심해서 사용해야함...

### 문제 해결

> 변경 전 StatefulService.java
```java
package spring.core.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    public void order(String name, int price){
        System.out.println( "name = " + name + " price = " + price );
        this.price = price; // 여기가 문제!
    }

    public int getPrice() {
        return price;
    }
}

```
> 변경 후 StatfulService.java
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

        //ThreadA : A사용자 10000원 주문
       int priceA =  statefulService1.order("userA",10000);
        //ThreadB : B사용자 20000원 주문
        int priceB = statefulService1.order("userB",20000);

        //ThreadA : 사용자A 주문 금액 조회
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

- 출력 확인

- <br>

![싱글톤 문제점 2](https://user-images.githubusercontent.com/52389219/152278252-eb8e08ee-72ef-4969-ae81-761cce1cf650.PNG)

👉 참조 : [인프런](https://www.inflearn.com/)의 **김영한님**강의 , [남궁성님의 자바의정석]


<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄

<br>

**개인메모**

