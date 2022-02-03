# 📌 웹 어플리케이션과 싱글톤 1

- 웹 어플리케이션은 보통 여러 고객이 동시에 요청을 한다.

![웹 어플리케이션과 싱글톤 1](https://user-images.githubusercontent.com/52389219/152261129-b49c499e-cb47-4ddf-aaeb-45273f6ad8f1.PNG)


<br>

```java
 public class SingletonTest {

  @Test
  @DisplayName("스프링 없는 순수한 DI 컨테이너")
  void pureContainer() {
    AppConfig appConfig = new AppConfig();

    //1.조회 : 호출할 때 마다 객체를 생성
    MemberService memberService1 = appConfig.memberService();

    //2.조회 : 호출할 때 마다 객체를 생성
    MemberService memberService2 = appConfig.memberService();

    //참조값이 다른것을 확인
    System.out.println("memberService1 : " + memberService1);

    System.out.println("memberService2 : " + memberService2);

    Assertions.assertThat(memberService1).isNotSameAs(memberService2);

  }

}
 ```

#### - 스프링 없는 순수한 DI 컨테이너인 AppConfig는 요청을 할 때 마다 객체를 새롭게 생성한다.
#### - 고객 트래픽이 초당 100이 나오면 초당 100개의 객체가 생성되고 소멸된다.
#### - 해당 객체가 딱 1개만 생성되고, 공유하도록 싱글톤 패턴을 적용해야한다.

# 📌 싱글톤 패턴

- 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴이다.
- 객체 인스턴스를 2개이상 생성하지 못하도록 막아야한다.
- private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야한다. 

<br>

```java
package spring.core.singleton;

public class SingletonService {

    // 1.static 영역에 객체를 딱 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    // 2. public 으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
```
- static 영역에 객체 instance를 하나 생성해서 올려둠
- 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회가능.
- 이 메서드를 호출하면 항상 같은 인스턴스 반환한다.
- 딱 1개의 객체 인스턴스만 존재해야하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는것을 막는다.

<br>


![싱글톤 패턴 2](https://user-images.githubusercontent.com/52389219/152266587-2a12876a-4318-4ade-9dd7-171fbc2393b4.PNG)

<BR>

- private으로 new 키워드를 막아두었다.
- 호출할 때 마다 같은 객체 인스턴스를 반환하는 것을 확인할 수 있다.


### 싱글톤 패턴의 문제점
##### - 구현하는 코드 자체가 많이 들어감 
##### - 의존관계상 클라이언트가 구체 클래스에 의존한다.
##### - 테스트하기 어렵고 내부 속성을 변경하거나 초기화 하기 어렵다.
##### - 유연성이 떨어진다.


# 📌 싱글톤 컨테이너 (=스프링 컨테이너)

### > - 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤으로 관리한다.
### > - 스프링 빈이 바로 싱글톤으로 관리되는 빈이다.


> 싱글톤 컨테이너

- 스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
- 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다.
- 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라고 한다.
- 싱글톤 패턴을 위한 복잡한 코드가 들어가지 않아도된다.
- DIP,OPP,테스트,private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다.

#### 스프링 컨테이너를 사용하는 테스트 코드 !

```java
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤!")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회 : 호출할 때 마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);

        //2. 조회 : 호출할 때 마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);
        
        //참조값이 같은 것 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
```

#### 위 코드의 결과값을 보면 주소값이 동일 한 것을 볼 수 있다.

<br>

![싱글톤 패턴 3](https://user-images.githubusercontent.com/52389219/152272544-29dd3d14-f201-4543-a27a-0250a46c6616.PNG)

#### - 아래 그림 처럼 이미 만들어진 객체를 공유해서 효율적으로 재사용 할 수있게 되었다.

![싱글톤 패턴 4](https://user-images.githubusercontent.com/52389219/152272639-aaf46363-89ee-4324-b66a-c650fb8606c3.PNG)


👉 참조 : [인프런](https://www.inflearn.com/)의 **김영한님**강의 , [남궁성님의 자바의정석]


<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄

<br>

**개인메모**

