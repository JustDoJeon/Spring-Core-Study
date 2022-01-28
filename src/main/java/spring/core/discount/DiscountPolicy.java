package spring.core.discount;

import spring.core.member.Member;

//할인 정책 인터페이스
public interface DiscountPolicy {
    int discount(Member member, int price);

}
