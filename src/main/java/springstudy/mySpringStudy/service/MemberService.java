package springstudy.mySpringStudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.mySpringStudy.domain.Member;
import springstudy.mySpringStudy.repository.MemberRepository;
import springstudy.mySpringStudy.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional  //JPA 를 사용하려면 필수!!(데이터 저장, 변경 시 필수)
public class MemberService {
    private final MemberRepository memberRepository;

    //    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
        long start = System.currentTimeMillis();

        try {
            //같은 이름이 있는 중복 회원X
            validateDuplicateMember(member);  //핵심 관심 사항(나머지: 공통 관심 사항)
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();  //핵심 관심 사항(나머지: 공통 관심 사항)
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(long memberId) {
        return memberRepository.findById(memberId);
    }
}
