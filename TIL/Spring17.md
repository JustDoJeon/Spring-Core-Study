# π λΉ μλͺμ£ΌκΈ° μ½λ°± 1

## λΉ μλͺμ£ΌκΈ° μ½λ°± μμ

- λ°μ΄ν°λ² μ΄μ€ μ»€λ₯μ νμ΄λ λ€νΈμν¬ μμΌμ²λΌ μ νλ¦¬μΌμ΄μ μμμμ μ νμν μ°κ²°μ λ―Έλ¦¬ν΄λκ³  μ’λ£μμ μ λͺ¨λ μ’λ£νλ μμμ μ§ννλ €λ©΄, κ°μ²΄μ μ΄κΈ°νμ μ’λ£ μμμ΄ νμνλ€.

<br>

> μ€νλ§ λΉμ λΌμ΄ν μ¬μ΄ν΄

- κ°μ²΄ μμ± -> μμ‘΄κ΄κ³ μ£Όμ
- μμ±μ μ£Όμμ κ²½μ° μμΈμ, κ°μ²΄μμ±κ³Ό μ£Όμμ ν¨κ»νλκΉ!
- μμ‘΄κ΄κ³ μ£Όμμ΄ μλ£λλ©΄ μ€νλ§ λΉμκ² μ½λ°± λ©μλλ₯Ό ν΅ν΄ μ΄κΈ°ν μμ μ μλ €μ£Όλ λ€μν κΈ°λ₯ μ κ³΅
- μ€νλ§ μ»¨νμ΄λκ° μ’λ£λκΈ° μ§μ μ μλ©Έ μ½λ°±μ μ£ΌκΈ°λνλ€.

<br>

> μ€νλ§ λΉμ μ΄λ²€νΈ λΌμ΄νμ¬μ΄ν΄

1) μ€νλ§ μ»¨νμ΄λ μμ±
2) μ€νλ§ λΉ μμ±
3) μμ‘΄κ΄κ³ μ£Όμ
4) μ΄κΈ°ν μ½λ°±
5) μ¬μ©
6) μλ©Έμ  μ½λ°±
7) μ€νλ§ μ’λ£

- μ΄κΈ°ν μ½λ°± : λΉμ΄ μμ±λκ³ , λΉμ μμ‘΄κ΄κ³ μ£Όμμ΄ μλ£λ ν νΈμΆ
- μλ©Έμ  μ½λ°± : λΉμ΄ μλ©ΈλκΈ° μ§μ μ νΈμΆ

<BR>

> κ°μ²΄μ μμ±κ³Ό μ΄κΈ°νλ₯Ό λΆλ¦¬ν΄μΌνλ€.

- μμ±μλ νμ μ λ³΄(νλΌλ―Έν°)λ₯Ό λ°κ³ , λ©λͺ¨λ¦¬λ₯Ό ν λΉν΄μ κ°μ²΄λ₯Ό μμ±νλ μ±μμ κ°μ§λ€.
- λ°λ©΄μ μ΄κΈ°νλ μ΄λ κ² μμ±λ κ°λ€μ νμ©ν΄μ μΈλΆ μ»€λ₯μμ μ°κ²°νλλ± λ¬΄κ±°μ΄ λμμ μννλ€
- μμ±μ μμμ λ¬΄κ±°μ΄ μ΄κΈ°ν μμμ ν¨κ» νλ κ² λ³΄λ€λ κ°μ²΄λ₯Ό μμ±νλ λΆλΆκ³Ό μ΄κΈ°ν νλ λΆλΆμ λͺννκ² λλλ κ²μ΄ μ μ§λ³΄μ κ΄μ μμ μ’λ€

<br>

> μ€νλ§μ ν¬κ² 3κ°μ§ λ°©λ²μΌλ‘ λΉ μλͺμ£ΌκΈ° μ½λ°±μ μ§μνλ€.

- μΈν°νμ΄μ€(InitializingBean, DisposableBean)
- μ€μ  μ λ³΄μ μ΄κΈ°ν λ©μλ, μ’λ£λ©μλ μ§μ 
- @PostConstruct, @PreDestroy μ λΈνμ΄μ μ§μ

#### μΈν°νμ΄μ€ InitializingBean, DisposableBean

```java
package spring.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {
    private String url;

    public NetworkClient() {
        System.out.println("μμ±μ νΈμΆ, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //μλΉμ€ μμμ νΈμΆ
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //μλΉμ€ μ’λ£μ νΈμΆ
    public void disConnect() {
        System.out.println("close + " + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("μ΄κΈ°ν μ°κ²° λ©μμ§");
    }

    @Override
    public void destroy() throws Exception {
        disConnect();
    }
}
```

<br>

- InitializingBeanμ afterPropertiesSet() λ©μλλ‘ μ΄κΈ°νλ₯Ό μ§μνλ€.
- DisposableBeanμ destroy() λ©μλλ‘ μλ©Έμ μ§μνλ€.

> μ΄κΈ°ν, μλ©Έ μΈν°νμ΄μ€ λ¨μ 

- μ΄ μΈν°νμ΄μ€λ μ€νλ§ μ μ© μΈν°νμ΄μ€λ€. ν΄λΉ μ½λκ° μ€νλ§ μ μ© μΈν°νμ΄μ€μ μμ‘΄νλ€.
- μ΄κΈ°ν, μλ©Έ λ©μλμ μ΄λ¦μ λ³κ²½ν  μ μλ€.
- λ΄κ° μ½λλ₯Ό κ³ μΉ  μ μλ μΈλΆ λΌμ΄λΈλ¬λ¦¬μ μ μ©ν  μ μλ€.

<br>

> λΉ λ±λ‘ μ΄κΈ°ν, μλ©Έ λ©μλ μ§μ 

- μ€μ  μ λ³΄μ @Bean(initMethod = "init", destroyMethod = "close") μ²λΌ μ΄κΈ°ν, μλ©Έ λ©μλλ₯Ό μ§μ ν  μ μλ€.

<br>

> μ€μ  μ λ³΄λ₯Ό μ¬μ©νλλ‘ λ³κ²½

```java
package spring.core.lifecycle;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("μμ±μ νΈμΆ, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //μλΉμ€ μμμ νΈμΆ
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //μλΉμ€ μ’λ£μ νΈμΆ
    public void disConnect() {
        System.out.println("close + " + url);
    }

    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("μ΄κΈ°ν μ°κ²° λ©μμ§");
    }

    public void close() {
        System.out.println("NetworkClient.close");
        disConnect();
    }
}
```

<br>

> μ€μ  μ λ³΄μ μ΄κΈ°ν μλ©Έ λ©μλ μ§μ 

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

> μ€μ  μ λ³΄ μ¬μ© νΉμ§
- λ©μλ μ΄λ¦μ μμ λ‘­κ² μ€ μ μλ€.
- μ€νλ§ λΉμ΄ μ€νλ§ μ½λμ μμ‘΄νμ§ μλλ€.
- μ½λ μλλΌ μ€μ  μ λ³΄λ₯Ό μ¬μ©νκΈ° λλ¬Έμ μ½λλ₯Ό κ³ μΉ μ μλ μΈλΆ λΌμ΄λΈλ¬λ¦¬μλ μ΄κΈ°ν, μ’λ£λ©μλλ₯Ό μ μ©ν  μ μλ€.


> μ’λ£ λ©μλ μΆλ‘ 
- @Beanμ destroyMethod μμ±μλ μμ£Ό νΉλ³ν κΈ°λ₯μ΄ μλ€.
- λΌμ΄λΈλ¬λ¦¬λ λλΆλΆ close , shutdown μ΄λΌλ μ΄λ¦μ μ’λ£ λ©μλλ₯Ό μ¬μ©νλ€
- @Beanμ destroyMethod λ κΈ°λ³Έκ°μ΄ (inferred) (μΆλ‘ )μΌλ‘ λ±λ‘λμ΄ μλ€.
- μ΄ μΆλ‘  κΈ°λ₯μ close , shutdown λΌλ μ΄λ¦μ λ©μλλ₯Ό μλμΌλ‘ νΈμΆν΄μ€λ€. μ΄λ¦ κ·Έλλ‘ μ’λ£ λ©μλλ₯Ό μΆλ‘ ν΄μ νΈμΆν΄μ€λ€.

π μ°Έμ‘° : [μΈνλ°](https://www.inflearn.com/)μ **κΉμνλ**κ°μ


<br>
π κ°μΈ κ³΅λΆ κΈ°λ‘μ© λΈλ‘κ·Έμλλ€. μ€λ₯λ νλ¦° λΆλΆμ΄ μμ κ²½μ° 
μΈμ λ μ§ λκΈ νΉμ λ©μΌλ‘ μ§μ ν΄μ£Όμλ©΄ κ°μ¬νκ² μ΅λλ€! π

<br>

**κ°μΈλ©λͺ¨**

