# π μμ‘΄ κ΄κ³ μλ μ£Όμ

## λ€μν μμ‘΄κ΄κ³ μ£Όμ λ°©λ²

- μμ±μ μ£Όμ
- μμ μ μ£Όμ(setter μ£Όμ)
- νλ μ£Όμ
- μΌλ° λ©μλ μ£Όμ

<br>

> μμ±μ μ£Όμ

- μ΄λ¦ κ·Έλλ‘ μμ±μλ₯Ό ν΅ν΄μ μμ‘΄ κ΄κ³λ₯Ό μ£Όμ λ°λ λ°©λ²μ΄λ€.
- μμ±μ νΈμΆ μμ μ λ± νλ²λ§ νΈμΆλλκ²μ΄ λ³΄μ₯λλ€.
- ν­μμ μλμ§λ§ λΆλ³, νμ μμ‘΄κ΄κ³μ μ¬μ©λλ€.

```java

@Component
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
}
```

<br>

> μμ μ μ£Όμ

- setterλΌ λΆλ¦¬λ νλμ κ°μ λ³κ²½νλ μμ μ λ©μλ ν΅ν΄μ μμ‘΄κ΄κ³λ₯Ό μ£Όμνλ λ°©λ²μ΄λ€.
- μ£Όλ‘ μ ν, λ³κ²½ κ°λ₯μ±μ΄ μλ μμ‘΄κ΄κ³μ μ¬μ©
- μλ°λΉ νλ‘νΌν° κ·μ½μ μμ μ λ©μλ λ°©μμ μ¬μ©νλ λ°©λ²μ΄λ€.

```java

@Component
public class OrderServiceImpl implements OrderService {
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}
```

- @Autowiredμ κΈ°λ³Έ λμμ μ£Όμν  λμμ΄ μμΌλ©΄ μ€λ₯κ° λ°μνλλ°, μ£Όμν  λμμ΄ μμ΄λ λμνκ² νλ €λ©΄ @Autowired(required = false)λ‘ μ§μ νλ©΄ λλ€.

> νλ μ£Όμ

- λ΄κ° μ€κ²¬SI λ€λλ λͺ¨λ  μ½λκ° νλμ£Όμμ΄μμ..
- μ΄λ¦ κ·Έλλ‘ νλμ λ°λ‘ μ£Όμνλ λ°©λ²μ΄λ€.
- μ½λκ° κ°κ²°νμ§λ§ μΈλΆμμ λ³κ²½μ΄ λΆκ°λ₯νκΈ°λλ¬Έμ νμ€νΈ νκΈ°κ° νλ€λ€.
- DI νλ μμν¬κ° μμΌλ©΄ μλ¬΄κ²λ ν  μ μλ€.
- μ£Όλ‘ μ€μ μ½λμ μκ΄μλ νμ€νΈμ½λμμλ§ μ¬μ©λλ€.

```java

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DiscountPolicy discountPolicy;
}
```

<br>

> μΌλ° λ©μλ μ£Όμ

- μΌλ° λ©μλλ₯Ό ν΅ν΄μ μ£Όμ λ°μ μ μλ€.
- νλ²μ μ¬λ¬ νλλ₯Ό μ£Όμ λ°μμ μμ§λ§ μΌλ°μ μΌλ‘ μ μ¬μ©νμ§ μλλ€.

```java

@Component
public class OrderServiceImpl implements OrderService {
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy
            discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
}
```

#### μμ±μ μ£Όμμ μ νν΄μΌνλ μ΄μ 
- λλΆλΆμ μμ‘΄κ΄κ³ μ£Όμμ νλ² μΌμ΄λλ©΄ μ νλ¦¬μΌμ΄μ μ’λ£μμ κΉμ§ μμ‘΄κ΄κ³λ₯Ό λ³κ²½ν  μΌμ΄ μλ€.
- κ·Έλ¬λ―λ‘ λΆλ³ν΄μΌν¨ 
- μμ μ μ£Όμμ μ¬μ©νλ©΄, setXxxλ©μλλ₯Ό publicμΌλ‘ μ΄μ΄λμ΄μΌνλ€.
- λκ΅°κ° μ€μλ‘ λ³κ²½ν  μλ μκ³ , λ³κ²½νλ©΄ μλλ λ©μλλ₯Ό μ΄μ΄λλκ²μ μ’μ μ€κ³ λ°©λ²μ΄ μλλ€.
- μμ±μ μ£Όμμ κ°μ²΄λ₯Ό μμ±ν  λ λ± 1λ²λ§ νΈμΆλλ―λ‘ μ΄νμ νΈμΆλλ μΌμ΄μλ€.


π μ°Έμ‘° : [μΈνλ°](https://www.inflearn.com/)μ **κΉμνλ**κ°μ , [λ¨κΆμ±λμ μλ°μμ μ]


<br>
π κ°μΈ κ³΅λΆ κΈ°λ‘μ© λΈλ‘κ·Έμλλ€. μ€λ₯λ νλ¦° λΆλΆμ΄ μμ κ²½μ° 
μΈμ λ μ§ λκΈ νΉμ λ©μΌλ‘ μ§μ ν΄μ£Όμλ©΄ κ°μ¬νκ² μ΅λλ€! π

<br>

**κ°μΈλ©λͺ¨**

