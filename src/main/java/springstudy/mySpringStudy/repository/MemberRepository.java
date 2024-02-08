package springstudy.mySpringStudy.repository;

import springstudy.mySpringStudy.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();

//    public void clearStore() {
//        store.clear();
//    }
}
