package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;
    //원래는 PersistenceContext가 있어야하는데 부트가 autowirde로도 넣어주게끔 한다.

    public void save(Member member){
        em.persist(member);
        //db에 들어간 시점이 아니어도 영속성컨텍스트에 올릴려면 아이디가 필요하기 때문에
        //db에 안들어가도 영속성컨텍스트에 아이디가 있다. sequece는 inclement는 다 된다.
    }
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }
    public List<Member>findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
    public List<Member>findByName(String name){
        return em.createQuery("select m from Member m where m.name=:userName", Member.class)
                .setParameter("userName", name)
                .getResultList();
    }
}
