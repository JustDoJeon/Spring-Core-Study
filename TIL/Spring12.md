# ๐ ์คํ๋ง ๋น ์ค์  ๋ฉํ ์ ๋ณด - BeanDefinition

<br>

![์คํ๋ง ๋ฉํ์ ๋ณด1](https://user-images.githubusercontent.com/52389219/152163924-bdb91445-2ba1-4750-b92c-5cfae43c3829.PNG)


> ์คํ๋ง ์ปจํ์ด๋๋ BeanDefinition๋ง ์์กดํ๋ค
>
> ์ฆ, class๋ก ์ค์ ๋๋  xml๋ก ์ค์ ๋๋  ์๊ด์ด ์์

- BeanDefinition ์ ๋น ์ค์  ๋ฉํ ์ ๋ณด๋ผ๊ณ ํ๋ค.
- @Bean , <bean>  ๋น ๊ฐ๊ฐ ํ๋์ฉ ๋ฉํ์ ๋ณด๊ฐ ์์ฑ๋๋ค.
- ์คํ๋ง ์ปจํ์ด๋๋ ์ด ๋ฉํ์ ๋ณด๋ฅผ ๊ธฐ๋ฐ์ผ๋ก ์คํ๋ง ๋น์ ์์ฑํ๋ค.

<br>

![์คํ๋ง ๋ฉํ์ ๋ณด2](https://user-images.githubusercontent.com/52389219/152163995-15dde8e0-620f-455f-8a6e-04f33a78f0af.PNG)

- AnnotationConfigApplicationContext ๋ AnnotatedBeanDefinitionReader ๋ฅผ ์ฌ์ฉํด์ Appconfig.class ๋ฅผ ์ฝ๊ณ  BeanDifinition์ ์์ฑํ๋ค.

- GenericXmlApplicationContext ๋ XmlBeanDefinitionReader ๋ฅผ ์ฌ์ฉํด์ appConfig.xml ์ค์ 
  ์ ๋ณด๋ฅผ ์ฝ๊ณ  BeanDefinition ์ ์์ฑํ๋ค.
- ์๋ก์ด ํ์์ ์ค์  ์ ๋ณด๊ฐ ์ถ๊ฐ๋๋ฉด, XxxBeanDefinitionReader๋ฅผ ๋ง๋ค์ด์ BeanDefinition ์
  ์์ฑํ๋ฉด ๋๋ค.

```java
public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("๋น ์ค์  ๋ฉํ ์ ๋ณด ํ์ธ")
    void findAppicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        "beanDefinition = " + beanDefinition);
            }

        }
    }

}
```

<br>

![๋น ๋ํผ๋์1](https://user-images.githubusercontent.com/52389219/152164325-4c5aa854-31eb-49ba-aec5-d1a1ac5629d4.PNG)

#### BeanDefinition ์ ๋ณด

- BeanClassName: ์์ฑํ  ๋น์ ํด๋์ค ๋ช(์๋ฐ ์ค์  ์ฒ๋ผ ํฉํ ๋ฆฌ ์ญํ ์ ๋น์ ์ฌ์ฉํ๋ฉด ์์)
- factoryBeanName: ํฉํ ๋ฆฌ ์ญํ ์ ๋น์ ์ฌ์ฉํ  ๊ฒฝ์ฐ ์ด๋ฆ, ์) appConfig 
- factoryMethodName: ๋น์ ์์ฑํ  ํฉํ ๋ฆฌ ๋ฉ์๋ ์ง์ , ์) memberService
- Scope: ์ฑ๊ธํค(๊ธฐ๋ณธ๊ฐ)
- lazyInit: ์คํ๋ง ์ปจํ์ด๋๋ฅผ ์์ฑํ  ๋ ๋น์ ์์ฑํ๋ ๊ฒ์ด ์๋๋ผ, ์ค์  ๋น์ ์ฌ์ฉํ  ๋ ๊น์ง ์ต๋ํ
  ์์ฑ์ ์ง์ฐ์ฒ๋ฆฌ ํ๋์ง ์ฌ๋ถ
- InitMethodName: ๋น์ ์์ฑํ๊ณ , ์์กด๊ด๊ณ๋ฅผ ์ ์ฉํ ๋ค์ ํธ์ถ๋๋ ์ด๊ธฐํ ๋ฉ์๋ ๋ช
- DestroyMethodName: ๋น์ ์๋ช์ฃผ๊ธฐ๊ฐ ๋๋์ ์ ๊ฑฐํ๊ธฐ ์ง์ ์ ํธ์ถ๋๋ ๋ฉ์๋ ๋ช
- Constructor arguments, Properties: ์์กด๊ด๊ณ ์ฃผ์์์ ์ฌ์ฉํ๋ค. (์๋ฐ ์ค์  ์ฒ๋ผ ํฉํ ๋ฆฌ
  ์ญํ ์ ๋น์ ์ฌ์ฉํ๋ฉด ์์)

<br>

>  BeanDefinition์ ์ค๋ฌด์์ ์ง์  ์ ์ํ๊ฑฐ๋ ์ฌ์ฉํ  ์ผ์ ๊ฑฐ์ ์๋ค๊ณ ํ๋ค..! ์์๋ง๋์! 


<br>

๐ ์ฐธ์กฐ : [์ธํ๋ฐ](https://www.inflearn.com/)์ **๊น์ํ๋**๊ฐ์ , [๋จ๊ถ์ฑ๋์ ์๋ฐ์์ ์] ,[๋ธ๋ก๊ทธ ์ฐธ๊ณ ](https://steady-coding.tistory.com/459) , 
[๋ธ๋ก๊ทธ ์ฐธ๊ณ ](https://kyun-s-world.gitbook.io/nowstart/spring/springframeworkcore/2-applicationcontext-2)

<br>
๐ ๊ฐ์ธ ๊ณต๋ถ ๊ธฐ๋ก์ฉ ๋ธ๋ก๊ทธ์๋๋ค. ์ค๋ฅ๋ ํ๋ฆฐ ๋ถ๋ถ์ด ์์ ๊ฒฝ์ฐ 
์ธ์ ๋ ์ง ๋๊ธ ํน์ ๋ฉ์ผ๋ก ์ง์ ํด์ฃผ์๋ฉด ๊ฐ์ฌํ๊ฒ ์ต๋๋ค! ๐

<br>

**๊ฐ์ธ๋ฉ๋ชจ**

