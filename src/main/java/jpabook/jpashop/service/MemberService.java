package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;//스프링꺼를 쓰자

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;//이게 도중에 바뀔수가 없다.

    @Transactional//이건 readOnly가 안먹힘
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //실패시에 예외를 터뜨린다.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        /*아이디라고 가정하고 아이디 중복확인시에 동시성문제가 있다 그래서 멀티쓰레드때문에 db에 유니크제약조건을 하는 것이 중요하다.*/
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //readOnly=> 읽기전용으로 바뀌어서 리소스를 적게써서 성능이 좋아진다.
    //영속성컨텍스트에서는 더티채킹을 안하고
    public List<Member>findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);

    }
}
