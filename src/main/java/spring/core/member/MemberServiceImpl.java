package spring.core.member;

public class MemberServiceImpl implements MemberService {

    // 가입을하고 회원을 찾으려면 멤버리포지토리가 필요함
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // AppConfig 생성과 함께 -> DIP를 지키게됨 구체적인 내용은 모르고 (Appconfig에서 생성함) 인터페이스만 의존
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
