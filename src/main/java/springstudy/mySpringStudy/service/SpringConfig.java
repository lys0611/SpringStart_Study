package springstudy.mySpringStudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springstudy.mySpringStudy.repository.JdbcMemberRepository;
import springstudy.mySpringStudy.repository.JdbcTemplateMemberRepository;
import springstudy.mySpringStudy.repository.MemberRepository;
import springstudy.mySpringStudy.repository.MemoryMemberRepository;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //DataSource
    //데이터베이스 커넥션을 획득할 때 사용하는 객체
    private DataSource dataSource;

    //스프링 부트는 데이터베이스 커넥션 정보를 바탕으로
    //DataSource를 생성하고 스프링 빈으로 만들어 두기 때문에
    //의존성 주입(DI)을 받을 수 있다.
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
