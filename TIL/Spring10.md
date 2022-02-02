# 📌 스프링 빈 조회 - 상속관계

>- 부모 타입으로 조회하면, 자식 타입도 함께 조회된다.
>-  그래서 모든 자바 객체의 최고 부모인 'Object' 타입으로 조회하면, 모든 스프링 빈을 조회한다.


![빈상속1](https://user-images.githubusercontent.com/52389219/152142872-15b0c636-c130-4202-8c47-49c7b1063006.PNG)


> 에러발생 : 현재 디스카운트 2가지에 대해 상속 관계이기 때문에 어떤 빈을 조회하는지 모른다. 

> 상속관계일땐, 아래의 방법들(메소드에 명시)로 조회가 가능하다.



### 빈 조회 상속관계일때, 여러가지 방법 코드들 

> 메소드 별로 테스트를 구분 지어 놨습니다.

```java
package spring.core.beanfind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.discount.RateDiscountPolicy;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));

    }

    //방법 1
    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면된다.")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class
        );

    }

    //방법2 ((좋진 않은 방법)
    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParaentType() {
        Map<String,DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
       for(String key : beansOfType.keySet()) {
           System.out.println("key = " + key + " value = " + beansOfType.get(key));
       }
    }





    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}


```



👉 참조 : [인프런](https://www.inflearn.com/)의 **김영한님**강의 , [남궁성님의 자바의정석] ,[블로그 참고](https://steady-coding.tistory.com/459)

<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄
<br>

**개인메모**

