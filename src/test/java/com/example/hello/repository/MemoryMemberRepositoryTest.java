package com.example.hello.repository;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.hello.domain.Member;

class MemoryMemberRepositoryTest {
	//Test 케이스는 순서가 보장되지 않으므로 순서에 의존적이면 안됨.
	//Test 하나가 끝나면 저장소나 공용데이터를 clear 해줘야함.
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	
	//메서드가 실행이 끝날때마다 동작하는 callback 메서드 
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		
		//Assertions 둘 다 가능
		
		//Assertions.assertArrayEquals(member, result);
		Assertions.assertThat(result).isEqualTo(member);
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		
		Assertions.assertThat(result).isEqualTo(member1);
		
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
	}

	
	
}
