# 📌 빈 생명주기 콜백 1

## 빈 생명주기 콜백 시작

- 데이터베이스 커넥션 풀이나 네트워크 소켓처럼 애플리케이션 시작시점에 필요한 연결을 미리해두고 종료시점에 모두 종료하는 작업을 진행하려면, 객체의 초기화와 종료 작업이 필요하다.

<br>

> 스프링 빈의 라이프 사이클

- 객체 생성 -> 의존관계 주입
- 생성자 주입의 경우 예외임, 객체생성과 주입을 함께하니깐!
- 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해 초기화 시점을 알려주는 다양한 기능 제공
- 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 주기도한다.

<br>

> 스프링 빈의 이벤트 라이프사이클

1) 스프링 컨테이너 생성
2) 스프링 빈 생성
3) 의존관계 주입
4) 초기화 콜백
5) 사용
6) 소멸전 콜백
7) 스프링 종료

- 초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
- 소멸전 콜백 : 빈이 소멸되기 직전에 호출

<BR>

> 객체의 생성과 초기화를 분리해야한다.

- 생성자는 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다.
- 반면에 초기화는 이렇게 생성된 값들을 활용해서 외부 커넥션을 연결하는등 무거운 동작을 수행한다
- 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체를 생성하는 부분과 초기화 하는 부분을 명확하게 나누는 것이 유지보수 관점에서 좋다

<br>

> 스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원한다.

- 인터페이스(InitializingBean, DisposableBean)
- 설정 정보에 초기화 메서드, 종료메서드 지정
- @PostConstruct, @PreDestroy 애노테이션 지원

#### 인터페이스 InitializingBean, DisposableBean

```java
package spring.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disConnect() {
        System.out.println("close + " + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        disConnect();
    }
}
```

<br>

- InitializingBean은 afterPropertiesSet() 메서드로 초기화를 지원한다.
- DisposableBean은 destroy() 메서드로 소멸을 지원한다.

> 초기화, 소멸 인터페이스 단점

- 이 인터페이스는 스프링 전용 인터페이스다. 해당 코드가 스프링 전용 인터페이스에 의존한다.
- 초기화, 소멸 메서드의 이름을 변경할 수 없다.
- 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.

<br>

> 빈 등록 초기화, 소멸 메서드 지정

- 설정 정보에 @Bean(initMethod = "init", destroyMethod = "close") 처럼 초기화, 소멸 메서드를 지정할 수 있다.

<br>

> 설정 정보를 사용하도록 변경

```java
package spring.core.lifecycle;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disConnect() {
        System.out.println("close + " + url);
    }

    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() {
        System.out.println("NetworkClient.close");
        disConnect();
    }
}
```

<br>

> 설정 정보에 초기화 소멸 메서드 지정

```java
@Configuration
static class LifeCycleConfig {
    @Bean(initMethod = "init", destroyMethod = "close")
    public NetworkClient networkClient() {
        NetworkClient networkClient = new NetworkClient();
        networkClient.setUrl("http://hello-spring.dev");
        return networkClient;
    }
}
```

<br>

> 설정 정보 사용 특징
- 메서드 이름을 자유롭게 줄 수 있다.
- 스프링 빈이 스프링 코드에 의존하지 않는다.
- 코득 아니라 설정 정보를 사용하기 때문에 코드를 고칠수 없는 외부 라이브러리에도 초기화, 종료메서드를 적용할 수 있다.


> 종료 메서드 추론
- @Bean의 destroyMethod 속성에는 아주 특별한 기능이 있다.
- 라이브러리는 대부분 close , shutdown 이라는 이름의 종료 메서드를 사용한다
- @Bean의 destroyMethod 는 기본값이 (inferred) (추론)으로 등록되어 있다.
- 이 추론 기능은 close , shutdown 라는 이름의 메서드를 자동으로 호출해준다. 이름 그대로 종료 메서드를 추론해서 호출해준다.

👉 참조 : [인프런](https://www.inflearn.com/)의 **김영한님**강의


<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄

<br>

**개인메모**

