## π μ€νλ§μ λν΄ μμλ³΄μ!

**ν΅μ¬ κΈ°μ ** : μ€νλ§ DI μ»¨νμ΄λ, AOP, μ΄λ²€νΈ λ±λ±

**μΉ κΈ°μ ** : μ€νλ§MVC, μ€νλ§ WebFlux

**λ°μ΄ν° μ κ·Ό κΈ°μ ** : νΈλμ­μ, JDBC, ORM μ§μ, XML μ§μ

**κΈ°μ ν΅ν©** : μΊμ, μ΄λ©μΌ, μκ²©μ κ·Ό, μ€μΌμ€λ§

**νμ€νΈ** : μ€νλ§ κΈ°λ° νμ€νΈ μ§μ

<br>
<br>

# π Spring Boot

- μ€νλ§μ νΈλ¦¬νκ² μ¬μ©ν  μ μλλ‘ μ§μ, μ΅κ·Όμλ κΈ°λ³ΈμΌλ‘ μ¬μ©
- λ¨λμΌλ‘ μ€νν  μ μλ μ€νλ§ μ νλ¦¬μΌμ΄μμ μ½κ² μμ±
- Tomcat κ°μ μΉ μλ²λ₯Ό λ΄μ₯νκΈ° λλ¬Έμ λ³λμ μΉ μλ²λ₯Ό μ€μΉνμ§ μμλ λ¨
- μμ¬μ΄ λΉλ κ΅¬μ±μ μν starter μ’μμ±μ μ κ³΅
- μ€νλ§κ³Ό μΈλΆ λΌμ΄λΈλ¬λ¦¬ μλ κ΅¬μ± (μ λ§ν λΌμ΄λΈλ¬λ¦¬λ λ²μ μ λν΄ κ³ λ―Όνμ§ μμλ μλμΌλ‘ κ΅¬μ±ν΄μ€λ€.)

<br>
<br>


# π Spring ν΅μ¬ κ°λ

- 1οΈβ£ μ€νλ§μ μλ°μΈμ΄κΈ°λ°μ νλ μμν¬κ³  __μλ° κ°λ° κ°μν__ μ λͺ©νλ₯Ό κ°κ³ μλ€.
- 2οΈβ£ μλ°μ κ°μ₯ ν° νΉμ§μ λ°λ‘ __κ°μ²΄ μ§ν₯ μΈμ΄__ λΌλκ²μ΄λ€. (POJO)
- 3οΈβ£ μ€νλ§μ κ°μ²΄ μ§ν₯μΈμ΄κ° κ°μ§ κ°λ ₯ν νΉμ§μ μ΄λ €λ΄λ νλ μμν¬λ€. (DI μ»¨νμ΄λ, IOC μ»¨νμ΄λ)
- 4οΈβ£ μ€νλ§μ κ²°κ΅­ __μ’μ κ°μ²΄ μ§ν₯ μ νλ¦¬μΌμ΄μ__ μ κ°λ°μλ€μ΄ κ°λ°ν  μ μλλ‘ λμμ£Όλ νλ μμν¬λ€. 

<br>
<br>

# π μ’μ κ°μ²΄ μ§ν₯κ³Ό κ°μ²΄ μ§ν₯ νλ‘κ·Έλλ°


β’ κ°μ²΄μ§ν₯ νλ‘κ·Έλλ°(Object Oriented Programming)μ λ¬Έμ λ₯Ό μ¬λ¬ κ°μ κ°μ²΄ λ¨μλ‘ λλ  μμνλ λ°©μμ λ§ν©λλ€. 

β’ κ°μ²΄μ§ν₯ νλ‘κ·Έλλ°μ κ°μ₯ ν° νΉμ§μ ν΄λμ€λ₯Ό μ΄μ©ν΄ μ°κ΄ μλ μ²λ¦¬λΆλΆ(ν¨μ)κ³Ό λ°μ΄ν° λΆλΆ(λ³μ)λ₯Ό νλλ‘ λ¬Άμ΄ κ°μ²΄(μΈμ€ν΄μ€)λ₯Ό μμ±ν΄ μ¬μ©νλ€λ μ μ΄λ€.

β’ κ°μ²΄μ§ν₯ νλ‘κ·Έλλ°μμλ νλ‘μ νΈλ₯Ό λλ¦½μ μΈ κ°μ²΄ λ¨μλ‘ λΆλ¦¬ν΄μ μμν  μ μκΈ° λλ¬Έμ μ¬λ¬ κ°λ°μμ νμν΄ κ·λͺ¨κ° ν° νλ‘μ νΈλ₯Ό μ§νν  μ μμΌλ©° μ μ§λ³΄μμΈ‘λ©΄λ λ°μ΄λλ€λ μ₯μ μ΄ μλ€.

β’ κ°μ²΄μ§ν₯μ νΉμ± μ€ λ€νμ±μ μ­ν κ³Ό κ΅¬νμΌλ‘ κ΅¬λΆνμ¬ μ΄λ₯Ό λ§μ‘±νλ κ°μ₯ ν° λ²μλΌκ³  μκ°νλ€.

β’ κ°μ²΄ μ€κ³μ, μ­ν (μΈν°νμ΄μ€)λ₯Ό λ¨Όμ  λΆμ¬νκ³ , κ·Έ μ­ν μ μννλ κ΅¬ν κ°μ²΄λ‘ λ§λ€μ΄μ λΆλ¦¬νλ©΄ μλμ°¨ -> μ κΈ°μ°¨,κΈ°λ¦μ°¨ λ±λ±.. μ²λΌ μ¬μ©νλ μ΄μ μλ μ΄μ λ²λ§ μλ©΄λκΈ°λλ¬Έμ μλμ°¨μ μμ₯μμ __νμ₯μ±__ μ΄ ν¬λ€.

β’ κ·Όλ° μμμ λ§ν μ­ν μΈ μΈν°νμ΄μ€κ° κΉ¨μ§λ©΄ ν΄λΌμ΄μΈνΈ, μλ² λͺ¨λ ν° λ³κ²½μ΄ λ°μνλ€. κ·Έλ¬λ―λ‘,μΈν°νμ΄μ€λ₯Ό μμ μ μΌλ‘ μ μ€κ³νλκ² κ°μ₯ μ€μνλ€κ³  νλ€.

<br>
<br>

# π Springκ³Ό κ°μ²΄ μ§ν₯


>β’ λ€νμ±μ΄ κ°μ₯ μ€μνλ€.<br>
>β’ Springμ λ€νμ±μ κ·Ήλνν΄μ μ΄μ©νκ² λμμ€λ€. <br>
>β’ Springμ IOC, DI λ λ€νμ±μ νμ©ν΄μ μ­νκ³Ό κ΅¬νμ νΈλ¦¬νκ² λ€λ£°μ μλλ‘ μ§μνλκ²μ΄λ€.


<br>
<br>

π μ°Έμ‘° : [μΈνλ°](https://www.inflearn.com/)μ κΉμνλ κ°μ, [λΈλ‘κ·Έ][Web Club](https://webclub.tistory.com/155), [λ¨κΆμ±λμ μλ°μμ μ] 

<br>
π κ°μΈ κ³΅λΆ κΈ°λ‘μ© λΈλ‘κ·Έμλλ€. μ€λ₯λ νλ¦° λΆλΆμ΄ μμ κ²½μ° 
μΈμ λ μ§ λκΈ νΉμ λ©μΌλ‘ μ§μ ν΄μ£Όμλ©΄ κ°μ¬νκ² μ΅λλ€! π
<br>

**κ°μΈλ©λͺ¨** 
μΆκ° κ³΅λΆν  λ΄μ© : κ°μ²΄μ§ν₯μ νΉμ§ λ€μ μ λ¦¬, μλ° μΈμ΄μ λ€νμ± 