# π μ€νλ§ λΉ μ‘°ν - μμκ΄κ³

>- λΆλͺ¨ νμμΌλ‘ μ‘°ννλ©΄, μμ νμλ ν¨κ» μ‘°νλλ€.
>-  κ·Έλμ λͺ¨λ  μλ° κ°μ²΄μ μ΅κ³  λΆλͺ¨μΈ 'Object' νμμΌλ‘ μ‘°ννλ©΄, λͺ¨λ  μ€νλ§ λΉμ μ‘°ννλ€.


![λΉμμ1](https://user-images.githubusercontent.com/52389219/152142872-15b0c636-c130-4202-8c47-49c7b1063006.PNG)


> μλ¬λ°μ : νμ¬ λμ€μΉ΄μ΄νΈ 2κ°μ§μ λν΄ μμ κ΄κ³μ΄κΈ° λλ¬Έμ μ΄λ€ λΉμ μ‘°ννλμ§ λͺ¨λ₯Έλ€. 

> μμκ΄κ³μΌλ, μλμ λ°©λ²λ€(λ©μλμ λͺμ)λ‘ μ‘°νκ° κ°λ₯νλ€.



### λΉ μ‘°ν μμκ΄κ³μΌλ, μ¬λ¬κ°μ§ λ°©λ² μ½λλ€ 

> λ©μλ λ³λ‘ νμ€νΈλ₯Ό κ΅¬λΆ μ§μ΄ λ¨μ΅λλ€.

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
    @DisplayName("λΆλͺ¨ νμμΌλ‘ μ‘°νμ, μμμ΄ λ μ΄μ μμΌλ©΄, μ€λ³΅ μ€λ₯κ° λ°μνλ€.")
    void findBeanByParentTypeDuplicate() {
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));

    }

    //λ°©λ² 1
    @Test
    @DisplayName("λΆλͺ¨ νμμΌλ‘ μ‘°νμ, μμμ΄ λ μ΄μ μμΌλ©΄, λΉ μ΄λ¦μ μ§μ νλ©΄λλ€.")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class
        );

    }

    //λ°©λ²2 ((μ’μ§ μμ λ°©λ²)
    @Test
    @DisplayName("νΉμ  νμ νμμΌλ‘ μ‘°ν")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("λΆλͺ¨ νμμΌλ‘ λͺ¨λ μ‘°ννκΈ°")
    void findAllBeanByParaentType() {
        Map<String,DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("λΆλͺ¨ νμμΌλ‘ λͺ¨λ μ‘°ννκΈ°")
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



π μ°Έμ‘° : [μΈνλ°](https://www.inflearn.com/)μ **κΉμνλ**κ°μ , [λ¨κΆμ±λμ μλ°μμ μ] ,[λΈλ‘κ·Έ μ°Έκ³ ](https://steady-coding.tistory.com/459)

<br>
π κ°μΈ κ³΅λΆ κΈ°λ‘μ© λΈλ‘κ·Έμλλ€. μ€λ₯λ νλ¦° λΆλΆμ΄ μμ κ²½μ° 
μΈμ λ μ§ λκΈ νΉμ λ©μΌλ‘ μ§μ ν΄μ£Όμλ©΄ κ°μ¬νκ² μ΅λλ€! π
<br>

**κ°μΈλ©λͺ¨**

