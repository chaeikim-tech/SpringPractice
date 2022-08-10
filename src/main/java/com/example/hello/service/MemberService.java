package com.example.hello.service;

import java.util.*;

import com.example.hello.domain.Member;
import com.example.hello.repository.MemberRepository;
import com.example.hello.repository.MemoryMemberRepository;


public class MemberService {
	
	private final MemberRepository memberRepository = new MemoryMemberRepository();
	
	/**
	 * 회원가입
	 */
	public Long join(Member member) {

		validateDuplicateMember(member); //중복 회원 검증		
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMember() {
		return memberRepository.findAll();
	}
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
