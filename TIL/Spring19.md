# π λΉ μ€μ½ν 1

## λΉ μ€μ½νλ?

- λΉμ΄ μ‘΄μ¬ν  μ μλ λ²μλ₯Ό λ»νλ€.

<br>


> λΉ μ€μ½νμ μ’λ₯

- μ±κΈν€ : κΈ°λ³Έ μ€μ½ν, μ€νλ§ μ»¨νμ΄λμ μμκ³Ό μ’λ£κΉμ§ μ μ§λλ κ°μ₯ λμ λ²μμ μ€μ½νλ€.
- νλ‘ν νμ : μ€νλ§ μ»¨νμ΄λλ νλ‘ν νμ λΉμ μμ±κ³Ό μμ‘΄κ΄κ³λ₯Ό μ£ΌμκΉμ§λ§ κ΄μ¬νκ³  λμ΄μ κ΄λ¦¬νμ§ μλ λ§€μ° μ§§μ λ²μμ μ€μ½νλ€
- μΉ κ΄λ ¨ μ€μ½ν
    - request : μΉ μμ²­μ΄ λ€μ΄μ€κ³  λκ°λκΉμ§ μ μ§λλ μ€μ½ν
    - session : μΉ μΈμμ΄ μμ±λκ³  μ’λ£λ  λ κΉμ§ μ μ§λλ μ€μ½ν
    - application : μΉμ μλΈλ¦Ώ μ»¨νμ€νΈμ κ°μ λ²μλ‘ μ μ§λλ μ€μ½νλ€.

<br>

> μ»΄ν¬λνΈ μ΄λΈνμ΄μμ μλλ±λ‘

```java

@Scope("prototype")
@Component
public class HelloBean {

}
```

<br>

> μλ λ±λ‘

```java
@Scope("prototype")
@Bean
PrototypeBean HelloBean(){
        return new HelloBean();
        }
```

>μ±κΈν€ λΉ μμ²­ κ³Όμ 

![μ±κΈν€ λΉ 1](https://user-images.githubusercontent.com/52389219/152459278-834e3cbe-9ea6-4723-81a6-967dd73e7b52.PNG)

1. μ±κΈν€ μ€μ½νμ λΉμ μ€νλ§ μ»¨νμ΄λμ μμ²­νλ€.
2. μ€νλ§ μ»¨νμ΄λλ λ³ΈμΈμ΄ κ΄λ¦¬νλ μ€νλ§ λΉμ λ°ννλ€.
3. μ΄νμ μ€νλ§ μ»¨νμ΄λμ κ°μ μμ²­μ΄ μλ κ°μ κ°μ²΄ μΈμ€ν΄μ€μ μ€νλ§ λΉμ λ°ννλ€.

<br>

### νλ‘ν  νμ μ€μ½ν

- μ±κΈν€ μ€μ½νμ λΉμ μ‘°ννλ €λ©΄ μ€νλ§ μ»¨νμ΄λλ ν­μ κ°μ μΈμ€ν΄μ€μ μ€νλ§ λΉμ λ°ννλ€.


- λ°λ©΄, νλ‘ν νμμ μ€μ½νλ₯Ό μ»¨νμ΄λμ μ‘°ννλ©΄ μ€νλ§ μ»¨νμ΄λλ ν­μ μλ‘μ΄ μΈμ€ν΄μ€λ₯Ό μμ±ν΄μ λ°ννλ€.

<br>

![μ±κΈν€ λΉ 2](https://user-images.githubusercontent.com/52389219/152459277-efa5eb60-1e34-43c2-8148-27181031173d.PNG)

1. νλ‘ν νμ μ€μ½νμ λΉμ μ€νλ§ μ»¨νμ΄λμ μμ²­νλ€.
2. μ€νλ§ μ»¨νμ΄λλ μ΄ μμ μ νλ‘ν νμ λΉμ μμ±νκ³ , νμν μμ‘΄κ΄κ³λ₯Ό μ£Όμνλ€.

<br>

![μ±κΈν€ λΉ 3](https://user-images.githubusercontent.com/52389219/152459272-e1c015a8-3857-4182-a5b8-f920941e795e.PNG)
3. μ€νλ§ μ»¨νμ΄λλ μμ±ν νλ‘ν νμ λΉμ ν΄λΌμ΄μΈνΈμ λ°ννλ€.
4. μ΄νμ μ€νλ§ μ»¨νμ΄λμ κ°μ μμ²­μ΄ μ€λ©΄ ν­μ μλ‘μ΄ νλ‘ν νμ λΉμ μμ±ν΄μ λ°ννλ€.




> νλ‘ν  νμμ νΉμ§ μ λ¦¬
- μ€νλ§ μ»¨νμ΄λμ μμ²­ν  λ λ§λ€ μλ‘ μμ±λλ€.

- μ€νλ§ μ»¨νμ΄λλ νλ‘ν νμ λΉμ μμ±κ³Ό μμ‘΄κ΄κ³ μ£Όμ κ·Έλ¦¬κ³  μ΄κΈ°νκΉμ§λ§ κ΄μ¬νλ€.


- μ’λ£ λ©μλκ° νΈμΆλμ§ μλλ€.


- νλ‘ν νμ λΉμ νλ‘ν νμ λΉμ μ‘°νν ν΄λΌμ΄μΈνΈκ° κ΄λ¦¬ν΄μΌ νλ€. μ’λ£ λ©μλμ λν νΈμΆλ
  ν΄λΌμ΄μΈνΈκ° μ§μ  ν΄μΌνλ€.

<br>


### μΉ μ€μ½ν


![μΉ μ€μ½ν1](https://user-images.githubusercontent.com/52389219/152460583-8b2c0fd0-e257-4ce5-99f9-df4f65c95d26.PNG)

- μΉ μ€μ½νλ μΉ νκ²½μμλ§ λμνλ€.
- μΉ μ€μ½νλ μ€νλ§μ΄ ν΄λΉ μ€μ½νμ μ’λ£μμ κΉμ§ κ΄λ¦¬νλ€.
- μ’λ£λ©μλκ° νΈμΆλλ€.

<br>




π μ°Έμ‘° : [μΈνλ°](https://www.inflearn.com/)μ **κΉμνλ**κ°μ


<br>
π κ°μΈ κ³΅λΆ κΈ°λ‘μ© λΈλ‘κ·Έμλλ€. μ€λ₯λ νλ¦° λΆλΆμ΄ μμ κ²½μ° 
μΈμ λ μ§ λκΈ νΉμ λ©μΌλ‘ μ§μ ν΄μ£Όμλ©΄ κ°μ¬νκ² μ΅λλ€! π

<br>

**κ°μΈλ©λͺ¨**

