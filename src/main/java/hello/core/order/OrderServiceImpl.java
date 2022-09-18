package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /* private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
       private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); */
    // 구현체인 DiscountPolicy를 위 주석처럼 구현하게되면 DIP 위반.. (추상에만 의존하도록 바꿔야함 = 인터페이스에만 의존하도록)

    private DiscountPolicy discountPolicy;
    // 구현체 없는데 어떻게 실행?
    // 누군가 클라이언트인 OrderServiceImpl 에 DiscountPolicy의 구현 객체를 대신 생성해서 주입해줘야함.
    // => AppConfig의 역할

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    
}
