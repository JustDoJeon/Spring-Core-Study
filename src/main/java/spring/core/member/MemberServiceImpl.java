package spring.core.member;

public class MemberServiceImpl implements MemberService {

    //가입을하고 회원을 찾으려면 멤버리포지토리가 필요함
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
