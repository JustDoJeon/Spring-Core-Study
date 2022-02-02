# 📌 BeanFactory와 ApplicationContext
<br>

![빈과 어플리케이션1](https://user-images.githubusercontent.com/52389219/152155475-00679a56-bdcf-47ca-9402-366ea13111ac.PNG)

### - BeanFactory
- #### 스프링 컨테이너의 최상위 인터페이스
- #### 스프링 빈을 관리하고 조회하는 역할을 담당하고, getBean()을 제공한다.
- #### 대부분의 기능을 BeanFactory에서 제공한다고 생각하면 된다.

### - ApplicationContext
- #### BeanFactory의 기능을 모두 상속받아서 제공한다.
- #### 빈을 관리하고 검색하는 기능을 BeanFactory가 제공해주지만 수많은 부가기능이 필요하다.


> ApplicationContext가 제공하는 부가기능들

![빈과 어플리케이션2](https://user-images.githubusercontent.com/52389219/152155494-45fac048-3083-4462-9800-fcf7d8d1777a.PNG)






1. 메세지 소스를 활용한 국제화 기능
- MessageSource는 메세지 설정 파일을 모아서 각 국가마다 로컬라이징을 함으로써 각 지역에 맞춤 메세지를 제공
   - 한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력하는 기능
   -기본 메세지의 경우  : message.properites (시스템의 언어 및 지역에 맞는 프로퍼티 파일이 존재하지 않을 경우)

   - 한글_한국 메세지 :     message_ko_KR.properties

   - 영어_미국 메세지 :     message_en_US.properties
   

2. 환경변수
- 로컬, 개발, 운영등을 구분해서 처리 
    - Profile(이하 프로파일) 활성화 및 설정
    - Environment의 역할은  Property(이하 프로퍼티) 소스 설정 및 프로퍼티 값을 가져오는 것입니다. 즉, 애플리케이션에 등록되는 key-value 쌍의 프로퍼티들을 접근할 수 있도록 해줍니다.
  
3. 애플리케이션 이벤트
- 이벤트를 발행하고 구독하는 모델을 편리하게 지원한다.

4. 편리한 리소스 조회
- 파일, 클래스 패스, 외부등에서 리소스를 편리하게 조회한다.





<br>

👉 참조 : [인프런](https://www.inflearn.com/)의 **김영한님**강의 , [남궁성님의 자바의정석] ,[블로그 참고](https://steady-coding.tistory.com/459)
[블로그 참고](https://kyun-s-world.gitbook.io/nowstart/spring/springframeworkcore/2-applicationcontext-2)
<br>
🌜 개인 공부 기록용 블로그입니다. 오류나 틀린 부분이 있을 경우 
언제든지 댓글 혹은 메일로 지적해주시면 감사하겠습니다! 😄
<br>

**개인메모**

