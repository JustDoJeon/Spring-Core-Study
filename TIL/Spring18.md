# ๐ ๋น ์๋ช์ฃผ๊ธฐ ์ฝ๋ฐฑ 2

## @PostConstruct & @PreDestroy

> ์์ ๋ง์ ๋ฐฉ๋ฒ ์ค๋ชํ์ง๋ง ๊ฒฐ๊ตญ ์ด ๋ฐฉ๋ฒ ์ฌ์ฉํ๋๊ฒ ์ ค๋ก ์ข์

<br>

```java
package spring.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("์์ฑ์ ํธ์ถ, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //์๋น์ค ์์์ ํธ์ถ
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //์๋น์ค ์ข๋ฃ์ ํธ์ถ
    public void disConnect() {
        System.out.println("close + " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("์ด๊ธฐํ ์ฐ๊ฒฐ ๋ฉ์์ง");
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

> @PostConstruct, @PreDestroy ์ ๋ธํ์ด์ ํน์ง
- ์ต์  ์คํ๋ง์์ ๊ฐ์ฅ ๊ถ์ฅํ๋ ๋ฐฉ๋ฒ์ด๋ค.
- ๊ฐํธํ๊ณ  ์คํ๋ง์ด ์๋ ๋ค๋ฅธ ์ปจํ์ด๋์์๋ ๋์ํ๋ค.
- ์ ์ผํ ๋จ์ ์ ์ธ๋ถ ๋ผ์ด๋ธ๋ฌ๋ฆฌ์๋ ์ ์ฉํ์ง ๋ชปํ๋ค๋๊ฒ์ธ๋ฐ ์ด๋ฐ ๊ฒฝ์ฐ, @Bean์  initMethod, destroyMethod ๋ฅผ ์ฌ์ฉํ๋ฉด๋๋ค. 



๐ ์ฐธ์กฐ : [์ธํ๋ฐ](https://www.inflearn.com/)์ **๊น์ํ๋**๊ฐ์


<br>
๐ ๊ฐ์ธ ๊ณต๋ถ ๊ธฐ๋ก์ฉ ๋ธ๋ก๊ทธ์๋๋ค. ์ค๋ฅ๋ ํ๋ฆฐ ๋ถ๋ถ์ด ์์ ๊ฒฝ์ฐ 
์ธ์ ๋ ์ง ๋๊ธ ํน์ ๋ฉ์ผ๋ก ์ง์ ํด์ฃผ์๋ฉด ๊ฐ์ฌํ๊ฒ ์ต๋๋ค! ๐

<br>

**๊ฐ์ธ๋ฉ๋ชจ**

