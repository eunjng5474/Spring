package com.multicampus.biz;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.multicampus.biz.user.UserService;
import com.multicampus.biz.user.UserVO;

public class UserServiceClient {
	public static void main(String[] args) {
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("business-layer.xml");
		
		// UserService 타입의 객체(UserServiceImpl)를 Lookup한다.
		UserService userService = (UserService) container.getBean("userService");
		
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test123");
		
		UserVO user = userService.getUser(vo);
		if(user != null) {
			System.out.println(user.getName() + "님 로그인 환영합니다.");
		} else {
			System.out.println("로그인 실패!!!");
		}
		
		container.close();
	}
}








