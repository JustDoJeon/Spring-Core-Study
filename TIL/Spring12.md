# 📌 스프링 빈 설정 메타 정보 - BeanDefinition

<br>

![스프링 메타정보1](https://user-images.githubusercontent.com/52389219/152163924-bdb91445-2ba1-4750-b92c-5cfae43c3829.PNG)


> 스프링 컨테이너는 BeanDefinition만 의존한다
>
> 즉, class로 설정되든 xml로 설정되든 상관이 없음

- BeanDefinition 을 빈 설정 메타 정보라고한다.
- @Bean , <bean>  당 각각 하나씩 메타정보가 생성된다.
- 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈을 생성한다.

<br>

![스프링 메타정보2](https://user-images.githubusercontent.com/52389219/152163995-15dde8e0-620f-455f-8a6e-04f33a78f0af.PNG)

- AnnotationConfigApplicationContext 는 AnnotatedBeanDefinitionReader 를 사용해서 Appconfig.class 를 읽고 BeanDifinition을 생성한다.

- GenericXmlApplicationContext 는 XmlBeanDefinitionReader 를 사용해서 appConfig.xml 설정
  정보를 읽고 BeanDefinition 을 생성한다.
- 새로운 형식의 설정 정보가 추가되면, XxxBeanDefinitionReader를 만들어서 BeanDefinition 을
  생성하면 된다.

```java
public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타 정보 확인")
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

![빈 디피니션1](https://user-images.githubusercontent.com/52389219/152164325-4c5aa854-31eb-49ba-aec5-d1a1ac5629d4.PNG)

#### BeanDefinition 정보

- BeanClassName: 생성할 빈의 클래스 명(자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음)
- factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름, 예) appConfig 
- factoryMethodName: 빈을 생성할 팩토리 메서드 지정, 예) memberService
- Scope: 싱글톤(기본값)
- lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때 까지 최대한
  생성을 지연처리 하는지 여부
- InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명
- DestroyMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명
- Constructor arguments, Properties: 의존관계 주입에서 사용한다. (자바 설정 처럼 팩토리
  역할의 빈을 사용하면 없음)

<br>

>  BeanDefinition을 실무에서 직접 정의하거나 사용할 일은 거의 없다고한다..! 알아만두자! 


<br>

👉 참조 : [인프런](https://www.inflearn.com/)의 **김영한님**강의 , [남궁성님의 자바의정석] ,[블로그 참고](https://steady-coding.tistory.com/459) , 
[블로그 참고](https://kyun-s-world.gitbook.io/nowstart/spring/springframeworkcore/2-applicationcontext-2)

<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄

<br>

**개인메모**

