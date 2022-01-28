## 📌 SOLID 

>클린 코드로 유명한 로버트 마틴이 좋은 객체 지향 설계의 5가지 원칙을 정리했다.


**SRP** : 단일 책임 원칙(single responsibility principle)

**OCP** : 개방-폐쇄 원칙 (Open/closed principle)

**LSP** : 리스코프 치환 원칙 (Liskov substitution principle)

**ISP** : 인터페이스 분리 원칙 (Interface segregation principle)

**DIP** : 의존관계 역전 원칙 (Dependency inversion principle)

<br>
<br>

# 🚀 SRP, 단일 책임원칙

- 한 클래스는 하나의 책임만 가져야한다.
- 하나의 책임이라는것은 클수도 있고, 작을수도 있고 문백과 상황에 따라 다르다.
- **중요한 기준은 변경**이다. 변경이 있을때 파급효과가 적으면 책임이 적은것으로 판단한다.

<br>

# OCP, 개방-폐쇄 원칙

- 소프트웨어 요소는 확장에는 열여 있으나 변경에는 닫혀있어야한다...(?)
- 다형성을 활용하자는 말이다.
- 인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능을 구현하는것이 역할과 구현의 분리로 확장성에 해당하는 말 인것 같다. (아래와 같이)

```java

public class MemberService {
    private MemberRepository memberRepository = new MemoryMemberRepository();
}
```
<br>

```java

public class MemberService {
    // private MemberRepository memberRepository  = new MemoryMemberRepository();
    private MemberRepository memberRepository  = new JdbcMemberRepository();
}
```

# OCP의 문제점

- MemberService 클라이언트가 구현클래스를 직접 선택 
-   - MemberRepository memberRepository  = new MemoryMemberRepository(); // 기존코드
-   - private MemberRepository memberRepository  = new JdbcMemberRepository(); //변경코드
- 구현 객체를 변경하려면 클라이언트 코드를 변경해야한다. 
- 다형성을 사용했지만 ocp가 깨짐 == 클라이언트가 코드를 변경해야한다.
- 👉 객체를 생성하고 연관관계를 맺어주는 별도의 조립, 설정자가 필요하다. 이 부분을 스프링 컨테이너가 해준다. (추후 포스팅내용에 코드와 함께)

# LSP, 리스코프 치환 원칙

- 프로그램의 객체는 프로그램의 정확성을 깨드리지않으면서 하위타입의 인스턴스로 바꿀 수 있어야한다.
- 다형성에서 하위클래스는 인터페이스 규약을 다 지켜야한다는 것, 다형성을 지원하기 위한 원칙, 인터페이스를 구현한 구현체는 이 원칙이 필요하다.
- 결국 상속의 과정 중 메소드의 재정의가 필요하다면 현재 자식 클래스가 부모 클래스의 기존 메소드의 의미를 해치지는 않는지 신중히 고민하고 올바르게 상속하라는 의미다.
- 하위형에서 메서드는 상위형 메서드에서 던져진 예외의 하위형을 제외하고 새로운 예외를 던지면 안된다.

# ISP, 인터페이스 분리 원칙

- 자신이 사용하지 않는 인터페이스는 구현하지 말아야한다는 원칙이다.
- 즉, 하나의 큰 인터페이스를 상속받기 보다는 인터페이스를 구체적이고 작은 단위들로 분리시켜 **꼭 필요한 인터페이스만 상속하자**는 의미다. 
- SRP -> 클래스의 단일책임 강조 , ISP -> 인터페이스의 단일책임 강조
- EX) 자동차 인터페이스 -> 운전 인터페이스, 정비 인터페이스로 분리
- EX) 사용자 클라이언트 -> 운전자 클라이언트, 정비사 클라이언트로 분리

# DIP, 의존관계 역전 원칙 
- 상위모듈은 하위 모듈에 의존해서는 안된다. 둘 다 추상화에 의존해야한다.
- 구현클래스에 의존하지말고, 인터페이스에 의존하라는 뜻이다.
- 아이폰이라는 클래스의 객체를 생성하고 메소드를 사용하는것보단 폰 인터페이스의 기능을 명시하고 구현받아서 사용해야한다.
- 즉, 역할에 의존하게 해야한다는것과 같다. 하지만 아래 예시는 DIP에 위반되어있다.

```java

public class MemberService {
    // private MemberRepository memberRepository  = new MemoryMemberRepository();
    private MemberRepository memberRepository  = new JdbcMemberRepository();
}
```

- OCP에서 설명한 코드를 보면 MemberService는 인터페이스에 의존하지만, 구현클래스도 동시에 의존한다.
- MemberService 클라이언트가 구현클래스를 직접 선택하기 때문이다.
- 👉이러한 OCP+DIP를 가능하게하고 다형성까지 가능하게 하기 위해서 스프링의 기능이 필요하다.

# 🚀 Spring 기능

- 1️⃣ DI(Dipendency Injection): 의존관계, 의존성 주입
-   - DI: 의존관계, 의존성 주입
-   - DI 컨테이너 제공
- 2️⃣ 클라이언트 코드의 변경없이 기능 확장
- 4️⃣ 쉽게 부품을 교체하듯이 개발이 가능!
<br>
<br>

# 👉 좋은 객체 지향과 객체 지향 프로그래밍


• 객체지향 프로그래밍(Object Oriented Programming)은 문제를 여러 개의 객체 단위로 나눠 작업하는 방식을 말합니다. 

• 객체지향 프로그래밍의 가장 큰 특징은 클래스를 이용해 연관 있는 처리부분(함수)과 데이터 부분(변수)를 하나로 묶어 객체(인스턴스)를 생성해 사용한다는 점이다.

• 객체지향 프로그래밍에서는 프로젝트를 독립적인 객체 단위로 분리해서 작업할 수 있기 때문에 여러 개발자와 협업해 규모가 큰 프로젝트를 진행할 수 있으며 유지보수측면도 뛰어나다는 장점이 있다.

• 객체지향의 특성 중 다형성은 역할과 구현으로 구분하여 이를 만족하는 가장 큰 범위라고 생각한다.

• 객체 설계시, 역할(인터페이스)를 먼저 부여하고, 그 역할을 수행하는 구현 객체로 만들어서 분리하면 자동차 -> 전기차,기름차 등등.. 처럼 사용하는 운전자는 운전법만 알면되기때문에 자동차의 입장에서 __확장성__ 이 크다.

• 근데 위에서 말한 역할인 인터페이스가 깨지면 클라이언트, 서버 모두 큰 변경이 발생한다. 그러므로,인터페이스를 안정적으로 잘 설계하는게 가장 중요하다고 한다.

<br>
<br>

# 👉 Spring과 객체 지향


>• 다형성이 가장 중요하다.<br>
>• Spring은 다형성을 극대화해서 이용하게 도와준다. <br>
>• Spring의 IOC, DI 는 다형성을 활용해서 역활과 구현을 편리하게 다룰수 있도록 지원하는것이다.


<br>
<br>

👉 참조 : [인프런](https://www.inflearn.com/)의 김영한님 강의, [블로그](https://jaeyeong951.medium.com/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5-5%EC%9B%90%EC%B9%99-solid-ac7d4d660f4d), [남궁성님의 자바의정석] , [위키백과](https://ko.wikipedia.org/wiki/SOLID_(%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%EC%84%A4%EA%B3%84)

<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄
<br>

**개인메모** 
추가 공부할 내용 : 객체지향의 특징 다시 정리, 자바 언어의 다형성 