package com.ssafy.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @component(value = "p")라고 한다면 
// Test에서 getBean("programmer")가 아닌 "p"라고 해야 함 
public class Programmer {
	// 캡슐화 
	@Autowired
	@Qualifier("laptop")
	private Computer computer;
	
	public Programmer() {
	}
	// 생성자 주입 
//	@Autowired
	public Programmer(Computer computer) {
		this.computer = computer;
	}
	
	// setter(설정자) 이용한 주입 
	@Autowired
	public void setComputer(@Qualifier("desktop") Computer computer) {
		this.computer = computer;
	}
	
	
	public void coding() {
		System.out.println(computer.getInfo()+"으로 개발을 합니다.");
	}
}
