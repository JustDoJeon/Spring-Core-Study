# 📌 스프링 컨테이너 생성

### 스프링 컨테이너 생성 과정

> AppConfig 클래스 

```java
//스프링 컨테이너 생성
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```

![스프링 컨테이너 생성1](https://user-images.githubusercontent.com/52389219/151916275-15b32e03-567b-4df2-b903-433ae980dac3.PNG)

- ApplicationContext와 BeanFactory를 스프링 컨테이너라고 하는데 BeanFactory를 직접 사용하는 경우가 없으므로 일반적으로 ApplicationContext를 스프링 컨텍스트라고 한다.


- ApplicationContext는 인터페이스다.


- 스프링 컨테이너는 XML기반으로 만들수도있고, 자바 설정 클래스를 통해 만들수 있다.


- 위에 AppConfig를 사용했던 방식은 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만든것이다.


- AnnotationConfigApplicationContext는 해당 인터페이스를 구현한 구현체가 되는것이다. 참고로 구현체 종류 엄청많음

<br>

### 스프링 빈 등록 

![스프링 컨테이너 생성2](https://user-images.githubusercontent.com/52389219/151916271-ee5e2e9a-66fe-468c-9420-0e5e1d0976db.PNG)

### 스프링 빈 의존관계

![스프링 컨테이너 생성4](https://user-images.githubusercontent.com/52389219/151916277-ea7f4a4a-5f2b-48b7-b4db-4293338ad81e.PNG)


- 스프링은 빈을 먼저 생성하고 그리고 의존관계를 주입하는 단계가 나누어져있음
- 그런데 위 그림과 같이 자바 코드로 빈을 등록하면 생성자를 호출하면서 의존관계도 주입된다. 



👉 참조 : [인프런](https://www.inflearn.com/)의 **김영한님**강의 , [남궁성님의 자바의정석] ,[블로그 참고](https://steady-coding.tistory.com/459)

<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄
<br>

**개인메모**_
스프링 컨테이너 종류에 대해 추가 학습및 정리가 필요할 것 같다.