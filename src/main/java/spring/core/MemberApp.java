package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;


public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();

//        MemberService memberService = appConfig.memberService();

        //이렇게 설정함으로써 해당클래스를 스프링이 빈으로 등록해서 관리한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //파라미터 1: 찾을 메서드 이름, 파라미터2 : 클래스
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        //AppConfig 설정 이후 위 코드로 변경
        //MemberService memberService = new MemberServiceImpl();


        Member member = new Member(1L, "전도현", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());


    }

}
