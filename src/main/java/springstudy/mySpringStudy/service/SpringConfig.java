package springstudy.mySpringStudy.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springstudy.mySpringStudy.aop.TimeTraceAop;
import springstudy.mySpringStudy.repository.*;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //DataSource
    //데이터베이스 커넥션을 획득할 때 사용하는 객체
//    private DataSource dataSource;

    //스프링 부트는 데이터베이스 커넥션 정보를 바탕으로
    //DataSource를 생성하고 스프링 빈으로 만들어 두기 때문에
    //의존성 주입(DI)을 받을 수 있다.
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    @PersistenceContext
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean  //AOP 적용 방법 2
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
