package spring.core.member;

//DB 확정이 안났기때문에
public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

}
