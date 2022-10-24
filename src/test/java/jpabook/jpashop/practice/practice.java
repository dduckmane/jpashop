package jpabook.jpashop.practice;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class practice {
    @Autowired
    EntityManager em;

    @Test

public void practice(){
        System.out.println(
                "1111111111111111111111111111"
        );
        Team team = new Team();
        team.setName("team");
        em.persist(team);
        Person person = new Person();
        person.setName("kim");
        person.setTeam(team);
        em.persist(person);

        em.flush();
//        em.clear();

        Team team1 = em.find(Team.class, team.getId());
        int size = team1.getPeople().size();
        Assertions.assertThat(size).isEqualTo(1);



    }
    @Test
    public void persist_test(){
        Person person = new Person();
        em.persist(person);
        List<Person> result = em.createQuery("select p from Person p", Person.class).getResultList();
        System.out.println("result"+result.size());

    }
}
