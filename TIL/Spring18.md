# 📌 빈 생명주기 콜백 2

## @PostConstruct & @PreDestroy

> 앞서 많은 방법 설명했지만 결국 이 방법 사용하는게 젤로 좋음

<br>

```java
package spring.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disConnect();
    }
}
```

<br>

```java
@Configuration
static class LifeCycleConfig {
    @Bean
    public NetworkClient networkClient() {
        NetworkClient networkClient = new NetworkClient();
        networkClient.setUrl("http://hello-spring.dev");
        return networkClient;
    }
}
```

<br>

> @PostConstruct, @PreDestroy 애노테이션 특징
- 최신 스프링에서 가장 권장하는 방법이다.
- 간편하고 스프링이 아닌 다른 컨테이너에서도 동작한다.
- 유일한 단점은 외부 라이브러리에는 적용하지 못한다는것인데 이런 경우, @Bean의  initMethod, destroyMethod 를 사용하면된다. 



👉 참조 : [인프런](https://www.inflearn.com/)의 **김영한님**강의


<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄

<br>

**개인메모**

