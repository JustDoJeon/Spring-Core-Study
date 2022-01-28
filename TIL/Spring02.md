# 프로젝트로 기술 실습 전 !

> 처음부터 스프링 기반이 아니라 순수한 자바로 개발을 하고 변경하는식의 프로젝트다.
> 생성할때만 부트 스타터로 프로젝트 생성함

```

개발 운영체제 : Window 10

프레임워크 : Spring Boot

개발 도구 : IntelliJ IDEA Commnunity Edition 2021.3

개발언어 : Java JDK11 사용

사용 서버 : Apache Tomcat

빌드 : gradle

DB : ORACLE 예정 

그 외 : Git
```

<br>

_## 📌 회원 도메인 개발

> 회원 등급

```java
public enum Grade {
    //요구사항대로 두 등급으로 개발한다.
    BASIC,
    VIP
}
```

<BR>

> 회원 엔티티

```java
public class Member {
    private Long id;
    private String name;
    private Grade grade;

    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

}
```

<br>

> 회원 저장소

- 회원 저장소 인터페이스

```java
public interface MemberReposiotry {
    void save(Member member);

    Member findById();
}
```

<br>

- 메모리 회원 저장소 구현체

```java
// 데이터베이스가 확정되지 않은상태에서의 구현체
public class MemoryMemberRepository implements MemberRepository {
    // 동시성 문제 발생시 -> ConcurrentHashMap 사용
    private static Map<Long, Member> store = new Map<Long, Member>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById() {
        return store.get(memberId);
    }

}
```

<br>

> 회원 서비스 인터페이스

```java
public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
```

<br>

> 회원 서비스 구현체

```java
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new
            MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
```

<Br>

> 회원 도메인 - 회원가입 main

```java
public class MemberApp {
    public static void main(String[] args) {
        MemberServcie memberServcie = new MemberService();
        Member member = new Member(1L, "아리아나그란데", Grade.VIP);
        memberService.join(member);

        Member findMember = memberServcie.findeById(member.getName());
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}

```
> 설명 및 배운것
* 현재 위 자바코드로 구현되어있는 로직은 스프링이 사용되지않은 순수 자바코드다.
* 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있다 추후에 고쳐가면서 공부를 할것이다!

<br>

# 
👉 참조 : [인프런](https://www.inflearn.com/)의 김영한님
강의 , [남궁성님의 자바의정석] , [위키백과](https://ko.wikipedia.org/wiki/SOLID_%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%EC%84%A4%EA%B3%84)

<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄
<br>

**개인메모**_
