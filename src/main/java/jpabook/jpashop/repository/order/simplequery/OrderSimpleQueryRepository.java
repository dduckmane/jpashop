package jpabook.jpashop.repository.order.simplequery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {
    /*이런식으로 따로 페키지를 만들어서 orderRepository와 분리를 한다.*/
    private final EntityManager em;
    public List<OrderSimpleQueryDto> findOrderDtos() {
        /*성능은 더 좋지만 화면에 의존하고 있다.*/
        /*조회데이터 차이가 많이 나지 않는다면 위의 함수를 사용하는 것이 맞다.*/
        return em.createQuery(
                        "select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name,o.orderDate, o.status, d.address)" +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}